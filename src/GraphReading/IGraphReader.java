package GraphReading;

import java.util.List;

import ProgramGraph.ProgramGraph;
import application.FieldReader;

public interface IGraphReader {
	public String parse(ProgramGraph g);
	public void addMethodReader(MethodReader r);
	public void addFieldReader(FieldReader r);
	public void addEdgeReader(IEdgeReader r);
	public void addNodeReader(INodeReader r);
	public List<MethodReader> getMethodReaders();
	public List<FieldReader> getFieldReaders();
}
