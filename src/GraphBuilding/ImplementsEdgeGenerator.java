package GraphBuilding;

import ProgramGraph.IEdge;
import ProgramGraph.INode;
import ProgramGraph.ImplementsEdge;
import ProgramGraph.ProgramGraph;

public class ImplementsEdgeGenerator implements IEdgeGenerator {

	@Override
	public void generateEdge(ProgramGraph pg) {
		for(INode node: pg.getINodes()){
			for(INode other: pg.getINodes()){
				if((node.getClassNode().interfaces != null) && node.getClassNode().interfaces.contains(other.getClassNode().name)){
					IEdge e = new ImplementsEdge(other, node);
					pg.addEdge(e);
				}
			}
		}
	}

}
