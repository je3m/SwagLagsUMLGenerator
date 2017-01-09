package application;

import org.objectweb.asm.tree.ClassNode;

public class ExtendsEdgeGenerator implements IEdgeGenerator{

	@Override
	public void generateEdge(ProgramGraph pg) {
		for(ClassNode node: pg.getNodes()){
			for(ClassNode other: pg.getNodes()){
				if((node.superName != null) && node.superName.equals(other.name)){
					Edge e = new ExtendsEdge(other, node);
					pg.addEdge(e);
				}
			}
		}
	}

}
