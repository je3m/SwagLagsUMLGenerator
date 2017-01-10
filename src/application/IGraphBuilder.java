package application;
import java.io.IOException;

public interface IGraphBuilder {
	public ProgramGraph build(String[] classes) throws IOException;

	public void addEdgeGenerator(IEdgeGenerator gen);

	public void setNodeGenerator(INodeGenerator n);
}
