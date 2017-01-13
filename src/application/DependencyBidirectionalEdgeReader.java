package application;

import java.util.ArrayList;

public class DependencyBidirectionalEdgeReader implements IEdgeReader{
	@Override
	public String getEdges(ArrayList<IEdge> edges) {
		String code = "";
		for(IEdge e : edges){
			if(e.getDescription().contains("dbidirectional")){
				code += Utilities.getClassName(e.getTail().name);
				code += " -> ";
				code += Utilities.getClassName(e.getHead().name);
				code += " [arrowhead=\"ovee\", arrowtail=\"ovee\", style=\"dashed\", dir=\"both\"";

				if(e.getDescription().contains("manyH")){
					code += ", taillabel=\"1..m\", labeldistance=3";
				}

				if(e.getDescription().contains("manyT")){
					code += ", headlabel=\"1..m\", labeldistance=3";
				}
				code += "];\n";
			}




		}
		return code;
	}
}
