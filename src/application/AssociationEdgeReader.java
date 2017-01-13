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
				code += " [arrowhead=\"ovee\", arrowtail=\"ovee\", style=\"solid\"";

				if(e.getDescription().contains("many")){
					code += ", headlabel=\"1..m\", labeldistance=3";
				}

				code += "];\n";
			}
		}
		return code;
	}

}
