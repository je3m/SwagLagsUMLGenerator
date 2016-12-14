package application;
import java.io.IOException;

public interface IGraphBuilder {
	public ProgramGraph build(String[] classes) throws IOException;
}
