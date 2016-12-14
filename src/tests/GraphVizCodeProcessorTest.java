package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import application.CodeProcessor;
import application.GraphVizCodeProcessor;

public class GraphVizCodeProcessorTest {
	@Test
	public void testProcess() throws IOException{
		CodeProcessor cp = new GraphVizCodeProcessor(new String[]{"java.lang.String"});
		cp.setFile(new File("./input_output/test.png"));
		cp.process();

	}
}
