package GraphReading;

import java.util.ArrayList;

import ProgramGraph.IEdge;

public class GraphVizEdgeReader implements IEdgeReader{

	@Override
	public String getEdges(ArrayList<IEdge> edges) {
		String code = "";
		for(IEdge e : edges){
			if(e.getDescription().contains("GraphVizEdge")){
				code += e.getDescription().substring(e.getDescription().indexOf(' ')+1);
			}
		}
		return code;
	}

}
