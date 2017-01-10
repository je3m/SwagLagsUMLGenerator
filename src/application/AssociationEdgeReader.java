package application;

import java.util.ArrayList;

public class AssociationEdgeReader implements IEdgeReader{

	@Override
	public String getEdges(ArrayList<Edge> edges) {
		String code = "";
		for(Edge e : edges){
			if(e.getDescription().equals("association")){
				code += GraphVizGraphReader.getClassName(e.getHead().name);
				code += " -> ";
				code += GraphVizGraphReader.getClassName(e.getTail().name);
				code += " [arrowhead=\"odiamond\", style=\"solid\"];\n";
			}
		}
		return code;
	}

}
