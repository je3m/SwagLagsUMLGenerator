package application;

import java.util.ArrayList;

public class ImplementsEdgeReader implements IEdgeReader {

	@Override
	public String getEdges(ArrayList<Edge> edges) {
		String code = "";
		for(Edge e : edges){
			if (e.getDescription().equals("implements")){
				code += GraphVizGraphReader.getClassName(e.getTail().name);
				code += " -> ";
				code += GraphVizGraphReader.getClassName(e.getHead().name);
				code += " [arrowhead=\"onormal\", style=\"dashed\"];\n";
			}
		}
		return code;
	}

}
