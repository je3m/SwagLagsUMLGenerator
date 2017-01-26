package GraphReading;

import java.util.ArrayList;

import ProgramGraph.INode;
import application.FieldReader;

public class GraphVizNodeReader implements INodeReader {

	@Override
	public String getCode(INode n, ArrayList<FieldReader> fr, ArrayList<MethodReader> mr) {
		String code = "";

		if(n.getDescription().contains("GraphVizNode")){
			code += n.getDescription().substring(n.getDescription().indexOf(' ')+1);
		}

		return code;
	}

}
