package application;

import java.io.IOException;
import java.util.HashSet;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

public class RecursiveNodeGenerator implements INodeGenerator {
	HashSet<String> visited = new HashSet<>();
	HashSet<String> blacklist = new HashSet<>();

	@Override
	public void generateNodes(ProgramGraph pg, String[] classes) throws IOException {
		for(String s : classes) {
			this.readClass(s, pg);
		}
	}

	public void addBlackListed(String arg){
		this.blacklist.add(arg);
	}

	private boolean isBlackListed(String path){
		for(String black: this.blacklist){
			if(path.startsWith(black)){
				return true;
			}
		}
		return false;
	}

	private void readClass(String s, ProgramGraph pg) throws IOException {
		s = s.replaceAll("\\.","/");
		if(this.visited.contains(s) || this.isBlackListed(s)){
			return;
		} else {
			this.visited.add(s);
		}
		System.out.println(s);
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
