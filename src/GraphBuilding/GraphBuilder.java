package GraphBuilding;
import java.io.IOException;
import java.util.ArrayList;

import ProgramGraph.ProgramGraph;


public class GraphBuilder implements IGraphBuilder{
	private INodeGenerator nodeGen = new BasicNodeGenerator();
	private ArrayList<IEdgeGenerator> edgeGens = new ArrayList<IEdgeGenerator>();
	private ArrayList<IEdgeChecker> edgeCheckers = new ArrayList<IEdgeChecker>();

	@Override
	public void addEdgeChecker(IEdgeChecker ec) {
		this.edgeCheckers.add(ec);
	}

	@Override
	public void addEdgeGenerator(IEdgeGenerator gen){
		this.edgeGens.add(gen);
	}
	@Override
	public ProgramGraph build(String[] classes) throws IOException {
		ProgramGraph pg = new ProgramGraph();

		this.generateNodes(pg, classes);
		this.generateEdges(pg);
		this.checkEdges(pg);

		return pg;
	}

	private void checkEdges(ProgramGraph pg){
		for(IEdgeChecker ec : this.edgeCheckers){
			ec.fixEdges(pg);
		}
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

	@Override
	public void setNodeGenerator(INodeGenerator ng){
		this.nodeGen = ng;
	}
}
