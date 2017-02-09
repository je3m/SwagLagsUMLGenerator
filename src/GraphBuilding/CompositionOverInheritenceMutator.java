package GraphBuilding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import GraphReading.IUserGraphMutator;
import GraphReading.MethodReader;
import ProgramGraph.GraphVizEdge;
import ProgramGraph.GraphVizNode;
import ProgramGraph.IEdge;
import ProgramGraph.INode;
import ProgramGraph.ProgramGraph;
import application.FieldReader;
import application.Utilities;

public class CompositionOverInheritenceMutator implements IUserGraphMutator {
	private List<FieldReader> fr;
	private List<MethodReader> mr;

	public CompositionOverInheritenceMutator(List<FieldReader> fr, List<MethodReader> mr) {
		this.fr = fr;
		this.mr = mr;
	}

	private GraphVizEdge makeEdge(IEdge e){
		GraphVizEdge ans = new GraphVizEdge(e.getIHead(), e.getITail());

		String code = "";


		code += Utilities.getClassName(e.getTail().name);
		code += " -> ";
		code += Utilities.getClassName(e.getHead().name);
		code += " [arrowhead=\"onormal\", style=\"solid\", color=\"orange\"];\n";

		ans.setCode(code);

		return ans;


	}

	@SuppressWarnings("unchecked")
	@Override
	public void mutate(ProgramGraph g) {
		HashSet<IEdge> edgesToKill = new HashSet<IEdge>();
		HashSet<IEdge> edgesToAdd = new HashSet<IEdge>();

		HashSet<INode> nodesToKill = new HashSet<INode>();
		HashSet<INode> nodesToAdd = new HashSet<INode>();


		for (IEdge e : g.getEdges()){
			if(e.getDescription().contains("extends")){
				for(MethodNode m: (List<MethodNode>) e.getTail().methods){
					if (m.name.contains("<init>")) {
						continue;
					}
					for(MethodNode n: (List<MethodNode>) e.getHead().methods ) {
						if(n.name.equals(m.name)) {
							nodesToKill.add(e.getIHead());
							nodesToKill.add(e.getITail());

							nodesToAdd.add(this.makeNode(e.getIHead()));
							nodesToAdd.add(this.makeNode(e.getITail()));

							edgesToKill.add(e);
							edgesToAdd.add(this.makeEdge(e));
						}
					}
				}
			}
		}

		for(IEdge e : edgesToKill){
			g.removeEdge(e);
		}

		for(IEdge e : edgesToAdd){
			g.addEdge(e);
		}

		for(INode n : nodesToKill){
			g.removeNode(n);
		}

		for(INode n : nodesToAdd){
			g.addNode(n);
		}
	}

	private INode makeNode(INode node) {
		GraphVizNode newNode = new GraphVizNode(node.getClassNode());

		String code = "";

		if(node.getDescription().equals("normal")) {
			ClassNode c = node.getClassNode();
			c.name = c.name.replaceAll("\\$", "_");
			code += Utilities.getClassName(c.name) + " [\n";
			code += "shape =\"record\",\n";
			code += "color =\"orange\",\n";
			code += "label = \"{";
			if((Opcodes.ACC_INTERFACE & c.access) != 0){
				//is an interface
				code += "\\<\\<interface\\>\\>\\n";
			} else if((Opcodes.ACC_ABSTRACT & c.access) != 0){
				//is an abstract class
				code += "\\<\\<abstract\\>\\>\\n";
			}
			code += Utilities.getClassName(c.name) + "|";
			List<FieldNode> fields = new ArrayList<FieldNode>();
			for(FieldReader r: this.fr){
				for(FieldNode n : r.getFields(c)){
					fields.add(n);
				}
			}
			for(FieldNode field: fields){
				if((field.access & Opcodes.ACC_PUBLIC) > 0){
					code += "+ ";
				} else if((field.access & Opcodes.ACC_PRIVATE) > 0){
					code += "- ";
				} else if((field.access & Opcodes.ACC_PROTECTED) > 0){
					code += "# ";
				}
				if ((field.access & Opcodes.ACC_STATIC) > 0){
					code += "static ";
				}
				code+= field.name + " : " + Utilities.getClassName(Type.getType(field.desc))+ "\\l";
			}	//
			code += "|";	//
			List<MethodNode> methods = new ArrayList<MethodNode>();	//
			for(MethodReader r: this.mr){
				for(MethodNode n : r.getMethods(c)){
					methods.add(n);
				}
			}
			for(MethodNode method: methods){
				if((method.access & Opcodes.ACC_PUBLIC) > 0){
					code += "+ ";
				} else if((method.access & Opcodes.ACC_PRIVATE) > 0){
					code += "- ";
				} else if((method.access & Opcodes.ACC_PROTECTED) > 0){
					code += "# ";
				} else if((method.access & Opcodes.ACC_DEPRECATED) > 0){
					code += "dep ";
				}
				if ((method.access & Opcodes.ACC_STATIC) > 0){
					code += "static ";
				}
				String methodName = method.name;
				if(methodName.equals("<init>")){
					//Replace with class name if it is a constructor
					methodName = Utilities.getClassName(c.name);
				} else if (methodName.equals("<clinit>")){
					methodName = Utilities.getClassName(c.name);
				}
				code+= " " + methodName +  "(";
				boolean hasArgs = false;
				for(Type argType : Type.getArgumentTypes(method.desc)){
					hasArgs = true;
					code += Utilities.getClassName(argType) + ", ";
				}
				if(hasArgs) {
					code = code.substring(0, code.length() - 2);
				}

				code += ") : " + Utilities.getClassName(Type.getReturnType(method.desc)) + "\\l";
			}

			code += "}\"];\n";
		}

		newNode.setCode(code);
		return newNode;

	}
}

