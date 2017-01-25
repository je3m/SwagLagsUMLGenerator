package GraphBuilding;

import java.util.HashSet;

import ProgramGraph.IEdge;
import ProgramGraph.ProgramGraph;

public class AssociationDependencyChecker implements IEdgeChecker{

	@Override
	public void fixEdges(ProgramGraph g) {
		HashSet<IEdge> gonDie = new HashSet<IEdge>();

		for(IEdge e: g.getEdges()){
			for(IEdge ne : g.getEdges()) {
				if ((e.getDescription().contains("dependency")) &&
						ne.getDescription().contains("association") &&
						e.getHead().equals(ne.getHead()) &&
						e.getTail().equals(ne.getTail())){
					gonDie.add(e);
				}
			}
		}

		for(IEdge e : gonDie){
			g.removeEdge(e);
		}

	}

}
