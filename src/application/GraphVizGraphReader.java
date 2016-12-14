package application;

import org.objectweb.asm.tree.ClassNode;

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
			} else {
				//not an interface
			}
		}

		code += "\n}";
		return code;
	}

}
