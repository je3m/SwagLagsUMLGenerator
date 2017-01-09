package application;

import org.objectweb.asm.tree.ClassNode;

public class ImplementsEdgeGenerator implements IEdgeGenerator {

	@Override
	public void generateEdge(ProgramGraph pg) {
		for(ClassNode node: pg.getNodes()){
			for(ClassNode other: pg.getNodes()){
				if((node.interfaces != null) && node.interfaces.contains(other.name)){
					Edge e = new ImplementsEdge(other, node);
					pg.addEdge(e);
				}
			}
		}
	}

}
