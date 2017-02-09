package GraphBuilding;

import java.util.HashSet;

import ProgramGraph.IEdge;
import ProgramGraph.ProgramGraph;

public class DuplicateDependencyEdgeChecker implements IEdgeChecker{
	private HashSet<IEdge> gonDie = new HashSet<IEdge>();

	@Override
	public void fixEdges(ProgramGraph g) {
		for(IEdge e: g.getEdges()){
			for(IEdge ne : g.getEdges()) {
				if (((e.getDescription().contains("dependency") &&
						ne.getDescription().contains("dependency")) ||
						(e.getDescription().contains("association") &&
								ne.getDescription().contains("association"))) &&
						e.getHead().equals(ne.getHead()) &&
						e.getTail().equals(ne.getTail()) && !e.equals(ne)){
					if(e.getDescription().contains("many")){
						this.gonDie.add(ne);
					}
				}

				if (((e.getDescription().contains("abidirectional") &&
						ne.getDescription().contains("abidirectional")) ||
						(e.getDescription().contains("dbidirectional") &&
								ne.getDescription().contains("dbidirectional"))) &&
						((e.getHead().equals(ne.getHead()) &&
								e.getTail().equals(ne.getTail()))
								|| (e.getHead().equals(ne.getTail()) &&
										e.getTail().equals(ne.getHead())))  && !e.equals(ne)){
					if(e.getDescription().contains("many")){
						this.gonDie.add(ne);
					}
				}
			}
		}

		for(IEdge e : this.gonDie){
			g.removeEdge(e);
		}
	}
}