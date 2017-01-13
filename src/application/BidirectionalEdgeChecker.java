package application;

import java.util.HashSet;

public class BidirectionalEdgeChecker implements IEdgeChecker{

	@Override
	public void fixEdges(ProgramGraph g) {
		HashSet<Edge> removeEdges = new HashSet<Edge>();
		HashSet<Edge> addEdges = new HashSet<Edge>();
		for(Edge e1 : g.getEdges()){
			for(Edge e2: g.getEdges()){
				if(e1.getHead().equals(e2.getTail()) &&
					e1.getTail().equals(e2.getHead())){
					if(e1.getDescription().contains("association") &&
							e2.getDescription().contains("association")){
						AssociationBidirectionalEdge testEdge = new AssociationBidirectionalEdge(e1.getTail(), e1.getHead());
						if(e2.getDescription().contains("many")){
							testEdge.setManyTail();
						}
						if(e1.getDescription().contains("many")){
							testEdge.setManyHead();
						}
						if(!addEdges.contains(testEdge)){
							removeEdges.add(e1);
							removeEdges.add(e2);
							AssociationBidirectionalEdge newEdge = new AssociationBidirectionalEdge(e1.getHead(), e1.getTail());
							if(e1.getDescription().contains("many")){
								newEdge.setManyTail();
							}
							if(e2.getDescription().contains("many")){
								newEdge.setManyHead();
							}
							
							addEdges.add(newEdge);
						}
					} else if (e1.getDescription().contains("dependency") &&
							e2.getDescription().contains("dependency")){
						if(!addEdges.contains(new DependencyBidirectionalEdge(e1.getTail(), e1.getHead()))){
							removeEdges.add(e1);
							removeEdges.add(e2);
							addEdges.add(new DependencyBidirectionalEdge(e1.getHead(), e1.getTail()));
						}
					}
				}
			}
		}
		for(Edge e: removeEdges){
			g.removeEdge(e);
		}
		for(Edge e: addEdges){
			g.addEdge(e);
		}
	}
}
