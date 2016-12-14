package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import application.GraphVizGenerator;

public class GraphVizCodeGeneratorTest {
	@Test
	public void testWriteFile() throws IOException{
		String contents = "digraph memes {rankdir=BT; ExtendsEdge [ shape =\"record\"," +
				"label = \"{application_ExtendsEdge||+ ExtendsEdge (ClassNode, ClassNode) : void\\l+ getDescription() : String\\l}\"];}";

		GraphVizGenerator gen = new GraphVizGenerator();

		gen.writeFile(new File("./input_output/temp.png"), contents);

	}
}
