package application;

import java.util.ArrayList;

public class AssociationEdgeReader implements IEdgeReader{

	@Override
	public String getEdges(ArrayList<Edge> edges) {
		String code = "";
		for(Edge e : edges){
			if(e.getDescription().contains("association")){

				code += Utilities.getClassName(e.getTail().name);
				code += " -> ";
				code += Utilities.getClassName(e.getHead().name);
				code += " [arrowhead=\"ovee\", style=\"solid\"";

				if(e.getDescription().contains("many")){
					code += ", label=\"1..m\"";
				}

				code += "];\n";
			}
		}
		return code;
	}

}
