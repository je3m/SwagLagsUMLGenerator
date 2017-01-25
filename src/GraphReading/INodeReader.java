package GraphReading;

import java.util.ArrayList;

import ProgramGraph.INode;
import application.FieldReader;

public interface INodeReader {

	public String getCode(INode n, ArrayList<FieldReader> fr, ArrayList<MethodReader> mr);
}
