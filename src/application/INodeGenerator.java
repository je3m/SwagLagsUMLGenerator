package application;

import java.io.IOException;

public interface INodeGenerator {
	public void generateNodes(ProgramGraph pg, String[] classes) throws IOException;
}
