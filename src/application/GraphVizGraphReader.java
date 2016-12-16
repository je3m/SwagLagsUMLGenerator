package application;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import jdk.internal.org.objectweb.asm.Opcodes;

public class GraphVizGraphReader implements IGraphReader {
	ArrayList<FieldReader> fieldReaders = new ArrayList<FieldReader>();
	ArrayList<MethodReader> methodReaders = new ArrayList<MethodReader>();

	public void addMethodReader(MethodReader r){
		this.methodReaders.add(r);
	}
	
	public void addFieldReader(FieldReader r){
		this.fieldReaders.add(r);
	}
	
	private String getClassName(String s){
		return s.substring(s.lastIndexOf('/') + 1);
	}

	private String getClassName(Type t) {
		String[] temp = t.getClassName().split("(\\.|\\/)");

		return temp[temp.length - 1];

	}

	@Override
	public String parse(ProgramGraph g) {
		String code = "digraph memes {";

		code += "rankdir=BT;\n";

		for(ClassNode c : g.getNodes()) {
			c.name = c.name.replaceAll("\\$", "_");
			code += this.getClassName(c.name) + " [\n";
			code += "shape =\"record\",\n";
			code += "label = \"{";
			if((Opcodes.ACC_INTERFACE & c.access) != 0){
				//is an interface
				code += "\\<\\<interface\\>\\>\\n";
			} else if((Opcodes.ACC_ABSTRACT & c.access) != 0){
				//is an interface
				code += "\\<\\<abstract\\>\\>\\n";
			}
			code += this.getClassName(c.name) + "|";
			List<FieldNode> fields = new ArrayList<FieldNode>();
			for(FieldReader r: fieldReaders){
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
				code+= field.name + " : " + this.getClassName(Type.getType(field.desc))+ "\\l";
			}
			code += "|";
			List<MethodNode> methods = new ArrayList<MethodNode>();
			for(MethodReader r: methodReaders){
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
					methodName = this.getClassName(c.name);
				} else if (methodName.equals("<clinit>")){
					methodName = this.getClassName(c.name);
				}
				code+= " " + methodName +  "(";
				boolean hasArgs = false;
				for(Type argType : Type.getArgumentTypes(method.desc)){
					hasArgs = true;
					code += this.getClassName(argType) + ", ";
				}
				if(hasArgs) {
					code = code.substring(0, code.length() - 2);
				}

				code += ") : " + this.getClassName(Type.getReturnType(method.desc)) + "\\l";
			}

			code += "}\"];\n";

		}

		for(Edge e :g.getEdges()){
			code += this.getClassName(e.getTail().name);
			code += " -> ";
			code += this.getClassName(e.getHead().name);
			if(e.getDescription().equals("extends")){
				code += " [arrowhead=\"onormal\", style=\"solid\"];\n";
			} else if (e.getDescription().equals("implements")){
				code += " [arrowhead=\"onormal\", style=\"dashed\"];\n";
			} else {
				throw new IllegalArgumentException();
			}
		}

		code += "\n}";
		return code;
	}

}
