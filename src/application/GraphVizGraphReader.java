package application;

import java.util.List;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import jdk.internal.org.objectweb.asm.Opcodes;

public class GraphVizGraphReader implements IGraphReader {

	@Override
	public String parse(ProgramGraph g) {
		String code = "digraph memes {";

		code += "rankdir=BT\n";

		for(ClassNode c : g.getNodes()) {
			code += c.name + " [\n";
			code += "shape =\"record\"\n";
			code += "label = \"{";
			if((Opcodes.ACC_INTERFACE & c.access) != 0){
				//is an interface
				code += "\\<\\<interface\\>\\>\\n";
			}
			code += c.name + "|";
			List<FieldNode> fields = c.fields;
			for(FieldNode field: fields){
				if((field.access & Opcodes.ACC_PUBLIC) > 0){
					code += "+";
				} else if((field.access & Opcodes.ACC_PRIVATE) > 0){
					code += "-";
				} else if((field.access & Opcodes.ACC_PROTECTED) > 0){
					code += "#";
				}
				code+= " " + field.name + " : " + Type.getType(field.desc).getClassName() + "\\l";
			}
			code += "|";
			List<MethodNode> methods = c.methods;
			for(MethodNode method: methods){
				if((method.access & Opcodes.ACC_PUBLIC) > 0){
					code += "+";
				} else if((field.access & Opcodes.ACC_PRIVATE) > 0){
					code += "-";
				} else if((field.access & Opcodes.ACC_PROTECTED) > 0){
					code += "#";
				}
				code+= " " + field.name + " : " + Type.getType(field.desc).getClassName() + "\\l";
			}
		}

		code += "\n}";
		return code;
	}

}
