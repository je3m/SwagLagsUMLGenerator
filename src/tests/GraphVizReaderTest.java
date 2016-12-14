package tests;

import java.io.IOException;

import org.junit.Test;

import application.GraphBuilder;
import application.GraphVizGraphReader;
import application.ProgramGraph;

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
