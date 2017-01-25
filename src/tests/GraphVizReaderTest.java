package tests;

import java.io.IOException;

import org.junit.Test;

import GraphBuilding.GraphBuilder;
import ProgramGraph.ProgramGraph;
import application.GraphVizGraphReader;

public class GraphVizReaderTest {

	@Test
	public void testParse() throws IOException{
		GraphBuilder builder = new GraphBuilder();
		GraphVizGraphReader reader = new GraphVizGraphReader();

		ProgramGraph graph = builder.build(new String[]{"java.lang.Boolean"});

		String answer = reader.parse(graph);

		System.out.println(answer);
	}
}
