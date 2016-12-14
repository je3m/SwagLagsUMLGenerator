package application;

import java.util.List;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import jdk.internal.org.objectweb.asm.Opcodes;

public class GraphVizGraphReader implements IGraphReader {

	private String getClassName(Type t) {
		String[] temp = t.getClassName().split("(\\.|\\/)");

		return temp[temp.length - 1];

	}

	@Override
	public String parse(ProgramGraph g) {
		String code = "digraph memes {";

		code += "rankdir=BT;\n";

		for(ClassNode c : g.getNodes()) {
			code += getClassName(c.name) + " [\n";
			code += "shape =\"record\",\n";
			code += "label = \"{";
			if((Opcodes.ACC_INTERFACE & c.access) != 0){
				//is an interface
				code += "\\<\\<interface\\>\\>\\n";
			}
			code += getClassName(c.name) + "|";
			List<FieldNode> fields = c.fields;
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
			List<MethodNode> methods = c.methods;
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
					methodName = getClassName(c.name);
				} else if (methodName.equals("<clinit>")){
					methodName = getClassName(c.name);
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
		
		for(IEdge e :g.getEdges()){
			code += getClassName(e.getTail().name);
			code += " -> ";
			code += getClassName(e.getHead().name);
			if(e.getDescription().equals("extends")){
				code += " [arrowhead=\"onormal\", style=\"dashed\"];\n";
			} else {
				throw new IllegalArgumentException();
			}
		}

		code += "\n}";
		return code;
	}
	
	private String getClassName(String s){
		return s.substring(s.lastIndexOf('/') + 1);
	}

}
