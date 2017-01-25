package application;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class NormalNodeReader extends INodeReader{

	NormalNodeReader(ArrayList<FieldReader> fr, ArrayList<MethodReader> mr) {
		super(fr, mr);
	}

	@Override
	public String getCode(INode node) {
		String code = "";

		if(node.getDescription().equals("normal")) {
			ClassNode c = node.getClassNode();
			c.name = c.name.replaceAll("\\$", "_");
			code += Utilities.getClassName(c.name) + " [\n";
			code += "shape =\"record\",\n";
			code += "label = \"{";
			if((Opcodes.ACC_INTERFACE & c.access) != 0){
				//is an interface
				code += "\\<\\<interface\\>\\>\\n";
			} else if((Opcodes.ACC_ABSTRACT & c.access) != 0){
				//is an interface
				code += "\\<\\<abstract\\>\\>\\n";
			}
			code += Utilities.getClassName(c.name) + "|";
			List<FieldNode> fields = new ArrayList<FieldNode>();
			for(FieldReader r: this.fieldReaders){
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
			for(MethodReader r: this.methodReaders){
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

		return code;

	}

}
