package GraphBuilding;

import java.io.IOException;

import ProgramGraph.ProgramGraph;

public interface INodeGenerator {
	public void generateNodes(ProgramGraph pg, String[] classes) throws IOException;
}
