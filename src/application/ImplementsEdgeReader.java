package application;

import java.util.ArrayList;

public class ImplementsEdgeReader implements IEdgeReader {

	@Override
	public String getEdges(ArrayList<IEdge> edges) {
		String code = "";
		for(IEdge e : edges){
			if (e.getDescription().equals("implements")){
				code += Utilities.getClassName(e.getTail().name);
				code += " -> ";
				code += Utilities.getClassName(e.getHead().name);
				code += " [arrowhead=\"onormal\", style=\"dashed\"];\n";
			}
		}
		return code;
	}

}
