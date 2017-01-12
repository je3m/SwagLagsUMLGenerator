package application;

import java.util.ArrayList;

public class AssociationBidirectionalEdgeReader implements IEdgeReader{
	@Override
	public String getEdges(ArrayList<Edge> edges) {
		String code = "";
		for(Edge e : edges){
			if(e.getDescription().equals("abidirectional")){
				code += Utilities.getClassName(e.getTail().name);
				code += " -> ";
				code += Utilities.getClassName(e.getHead().name);
				code += " [arrowhead=\"ovee\", style=\"solid\", dir=\"both\"];\n";
			}
		}
		return code;
	}
}
