package application;

import java.util.HashSet;

public class DuplicateDependencyEdgeChecker implements IEdgeChecker{
	HashSet<IEdge> gonDie = new HashSet<IEdge>();

	@Override
	public void fixEdges(ProgramGraph g) {
		for(IEdge e: g.getEdges()){
			for(IEdge ne : g.getEdges()) {
				if ((e.getDescription().contains("dependency")) &&
						ne.getDescription().contains("dependency") &&
						e.getHead().equals(ne.getHead()) &&
						e.getTail().equals(ne.getTail())){
					if(e.getDescription().contains("many")){
						//						this.gonDie.add(ne);
					}
				}
			}
		}

		System.out.println(this.gonDie.size());
		for(IEdge e : this.gonDie){
			g.removeEdge(e);
		}
	}
}