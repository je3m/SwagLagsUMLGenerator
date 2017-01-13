package application;

import java.util.ArrayList;

public class DependencyBidirectionalEdgeReader implements IEdgeReader{
	@Override
	public String getEdges(ArrayList<Edge> edges) {
		String code = "";
		for(Edge e : edges){
			if(e.getDescription().equals("dbidirectional")){
				code += Utilities.getClassName(e.getTail().name);
				code += " -> ";
				code += Utilities.getClassName(e.getHead().name);
				code += " [arrowhead=\"ovee\", arrowtail=\"ovee\", style=\"dashed\", dir=\"both\"];\n";
			}
		}
		return code;
	}
}
