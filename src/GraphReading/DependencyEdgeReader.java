package GraphReading;

import java.util.ArrayList;

import ProgramGraph.IEdge;
import application.Utilities;

public class DependencyEdgeReader implements IEdgeReader{

	@Override
	public String getEdges(ArrayList<IEdge> edges) {
		String code = "";
		for(IEdge e : edges){

			if(e.getDescription().contains("dependency")){
				System.out.println(e.getHead().name +  " : " + e.getTail().name);
				code += Utilities.getClassName(e.getTail().name);
				code += " -> ";
				code += Utilities.getClassName(e.getHead().name);
				code += " [arrowhead=\"ovee\", style=\"dashed\"";

				if(e.getDescription().contains("many")){

					code += ", headlabel=\"1..m\", labeldistance=3";
				}

				code += "];\n";
			}
		}
		return code;
	}

}
