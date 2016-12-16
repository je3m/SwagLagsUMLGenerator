package application;
import java.io.IOException;

import org.objectweb.asm.tree.ClassNode;


public class GraphBuilder implements IGraphBuilder{
	INodeGenerator nodeGen = new BasicNodeGenerator();

	@Override
	public ProgramGraph build(String[] classes) throws IOException {
		ProgramGraph pg = new ProgramGraph();

		this.generateNodes(pg, classes);
		this.generateEdges(pg);

		return pg;
	}

	private void generateEdges(ProgramGraph pg) {
		for(ClassNode node: pg.getNodes()){
			for(ClassNode other: pg.getNodes()){
				if((node.superName != null) && node.superName.equals(other.name)){
					Edge e = new ExtendsEdge(other, node);
					pg.addEdge(e);
				} else if((node.interfaces != null) && node.interfaces.contains(other.name)){
					Edge e = new ImplementsEdge(other, node);
					pg.addEdge(e);
				}
			}
		}
	}

	private void generateNodes(ProgramGraph pg, String[] s) throws IOException{
		this.nodeGen.generateNodes(pg, s);
	}


	public void setNodeGenerator(INodeGenerator ng){
		this.nodeGen = ng;
	}
}
