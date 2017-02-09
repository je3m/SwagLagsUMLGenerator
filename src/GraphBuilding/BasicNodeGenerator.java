package GraphBuilding;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import ProgramGraph.ProgramGraph;

public class BasicNodeGenerator implements INodeGenerator{

	@Override
	public void generateNodes(ProgramGraph pg, String[] classes) throws IOException {
		for(String s : classes) {
			this.readClass(s, pg);
		}
	}

	private void readClass(String s, ProgramGraph pg) throws IOException {
		ClassReader reader = new ClassReader(s);
		ClassNode node = new ClassNode();
		reader.accept(node, ClassReader.EXPAND_FRAMES);

		pg.addNode(node);
	}
}
