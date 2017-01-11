package application;

import java.util.HashSet;

public class AssociationDependencyChecker implements IEdgeChecker{

	@Override
	public void fixEdges(ProgramGraph g) {
		HashSet<Edge> gonDie = new HashSet<Edge>();

		for(Edge e: g.getEdges()){
			for(Edge ne : g.getEdges()) {
				if ((e.getDescription().contains("dependency")) &&
						ne.getDescription().contains("association") &&
						e.getHead().equals(ne.getHead()) &&
						e.getTail().equals(ne.getTail())){
					gonDie.add(e);
				}
			}
		}

		for(Edge e : gonDie){
			g.removeEdge(e);
		}

	}

}
