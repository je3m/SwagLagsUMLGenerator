package application;
import java.io.IOException;
import java.util.ArrayList;


public class GraphBuilder implements IGraphBuilder{
	private INodeGenerator nodeGen = new BasicNodeGenerator();
	private ArrayList<IEdgeGenerator> edgeGens = new ArrayList<IEdgeGenerator>();

	public void addEdgeGenerator(IEdgeGenerator gen){
		this.edgeGens.add(gen);
	}

	@Override
	public ProgramGraph build(String[] classes) throws IOException {
		ProgramGraph pg = new ProgramGraph();

		this.generateNodes(pg, classes);
		this.generateEdges(pg);

		return pg;
	}
	private void generateEdges(ProgramGraph pg) {
		//The pinnacle of efficiency
		for(IEdgeGenerator gen : this.edgeGens) {
			gen.generateEdge(pg);
		}
	}

	private void generateNodes(ProgramGraph pg, String[] s) throws IOException{
		this.nodeGen.generateNodes(pg, s);
	}


	public void setNodeGenerator(INodeGenerator ng){
		this.nodeGen = ng;
	}
}
