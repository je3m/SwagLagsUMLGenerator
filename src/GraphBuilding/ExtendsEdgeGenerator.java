package GraphBuilding;

import ProgramGraph.ExtendsEdge;
import ProgramGraph.IEdge;
import ProgramGraph.INode;
import ProgramGraph.ProgramGraph;

public class ExtendsEdgeGenerator implements IEdgeGenerator{

	@Override
	public void generateEdge(ProgramGraph pg) {
		for(INode node: pg.getINodes()){
			for(INode other: pg.getINodes()){
				if((node.getClassNode().superName != null) && node.getClassNode().superName.equals(other.getClassNode().name)){
					IEdge e = new ExtendsEdge(other, node);
					pg.addEdge(e);
				}
			}
		}
	}

}
