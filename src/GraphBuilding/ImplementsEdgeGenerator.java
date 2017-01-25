package GraphBuilding;

import org.objectweb.asm.tree.ClassNode;

import ProgramGraph.IEdge;
import ProgramGraph.ImplementsEdge;
import ProgramGraph.ProgramGraph;

public class ImplementsEdgeGenerator implements IEdgeGenerator {

	@Override
	public void generateEdge(ProgramGraph pg) {
		for(ClassNode node: pg.getNodes()){
			for(ClassNode other: pg.getNodes()){
				if((node.interfaces != null) && node.interfaces.contains(other.name)){
					IEdge e = new ImplementsEdge(other, node);
					pg.addEdge(e);
				}
			}
		}
	}

}
