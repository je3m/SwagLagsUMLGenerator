package GraphBuilding;
import java.io.IOException;

import GraphReading.IUserGraphMutator;
import ProgramGraph.ProgramGraph;

public interface IGraphBuilder {
	public void addEdgeChecker(IEdgeChecker ec);

	public void addEdgeGenerator(IEdgeGenerator gen);

	public void addGraphMutator(IUserGraphMutator mut);

	public ProgramGraph build(String[] classes) throws IOException;

	public void setNodeGenerator(INodeGenerator n);


}
