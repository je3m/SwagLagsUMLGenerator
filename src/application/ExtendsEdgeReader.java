package application;

import java.util.ArrayList;

public class ExtendsEdgeReader implements IEdgeReader {

	@Override
	public String getEdges(ArrayList<Edge> edges) {
		String code = "";
		for(Edge e : edges){
			if(e.getDescription().equals("extends")){
				code += Utilities.getClassName(e.getTail().name);
				code += " -> ";
				code += Utilities.getClassName(e.getHead().name);
				code += " [arrowhead=\"onormal\", style=\"solid\"];\n";
			}
		}
		return code;
	}

}
