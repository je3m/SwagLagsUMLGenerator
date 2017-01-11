package application;
import java.io.IOException;

public interface IGraphBuilder {
	public void addEdgeChecker(IEdgeChecker ec);

	public void addEdgeGenerator(IEdgeGenerator gen);

	public ProgramGraph build(String[] classes) throws IOException;

	public void setNodeGenerator(INodeGenerator n);;
}
