package application;

import java.util.ArrayList;

public interface INodeReader {

	public String getCode(INode n, ArrayList<FieldReader> fr, ArrayList<MethodReader> mr);
}
