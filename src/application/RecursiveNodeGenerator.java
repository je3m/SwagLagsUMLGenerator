package application;

import java.io.IOException;
import java.util.HashSet;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

public class RecursiveNodeGenerator implements INodeGenerator {
	HashSet<String> visited = new HashSet<>();

	@Override
	public void generateNodes(ProgramGraph pg, String[] classes) throws IOException {
		for(String s : classes) {
			this.readClass(s, pg);
		}
	}

	private void readClass(String s, ProgramGraph pg) throws IOException {
		if(this.visited.contains(s)){
			return;
		} else {
			this.visited.add(s);
		}

		ClassReader reader = new ClassReader(s);
		ClassNode node = new ClassNode();
		reader.accept(node, ClassReader.EXPAND_FRAMES);

		pg.addNode(node);

		if(node.superName != null) {
			this.readClass(node.superName, pg);
		}

		if(node.interfaces != null) {
			for (int i = 0; i < node.interfaces.size(); i++){
				this.readClass((String) node.interfaces.get(i), pg);
			}
		}
	}
}
