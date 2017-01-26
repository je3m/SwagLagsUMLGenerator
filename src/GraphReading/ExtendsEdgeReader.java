package GraphReading;

import java.util.ArrayList;

import ProgramGraph.IEdge;
import application.Utilities;

public class ExtendsEdgeReader implements IEdgeReader {

	@Override
	public String getEdges(ArrayList<IEdge> edges) {
		String code = "";
		for(IEdge e : edges){
			if(e.getDescription().contains("extends")){
				code += Utilities.getClassName(e.getTail().name);
				code += " -> ";
				code += Utilities.getClassName(e.getHead().name);
				code += " [arrowhead=\"onormal\", style=\"solid\"];\n";
			}
		}
		return code;
	}

}
