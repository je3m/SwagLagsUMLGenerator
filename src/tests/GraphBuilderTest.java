package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import application.GraphBuilder;
import application.ProgramGraph;

public class GraphBuilderTest {

	@Test
	public void testBuildSingleClass() throws IOException{
		GraphBuilder builder = new GraphBuilder();
		ProgramGraph graph = builder.build(new String[]{"javax.swing.JTable"});
		assertEquals(graph.getNodes().get(0).name, "javax/swing/JTable");
		assertEquals(graph.getNodes().get(0).superName, "javax/swing/JComponent");
		
	}

	
	@Test
	public void testBuildInheritance() throws IOException{
		GraphBuilder builder = new GraphBuilder();
		ProgramGraph graph = builder.build(new String[]{"javax.swing.JTable", "javax.swing.JComponent"});
		assertEquals(graph.getNodes().size(), 2);
		assertEquals(graph.getNodes().get(0).name, "javax/swing/JTable");
		assertEquals(graph.getNodes().get(0).superName, "javax/swing/JComponent");
		assertEquals(graph.getNodes().get(1).name, "javax/swing/JComponent");
		assertEquals(graph.getEdges().size(),1);
		assertEquals(graph.getEdges().get(0).getHead().name, "javax/swing/JTable");
		assertEquals(graph.getEdges().get(0).getTail().name, "javax/swing/JComponent");
		assertEquals(graph.getEdges().get(0).getDescription(), "extends");
	}

}
