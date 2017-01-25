package application;

import java.util.ArrayList;

public abstract class INodeReader {
	protected ArrayList<FieldReader> fieldReaders;
	protected ArrayList<MethodReader> methodReaders;

	INodeReader(ArrayList<FieldReader> fr, ArrayList<MethodReader> mr){
		this.fieldReaders = fr;
		this.methodReaders = mr;
	}

	public abstract String getCode(INode n);
}
