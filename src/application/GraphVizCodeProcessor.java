package application;

import java.io.IOException;

import GraphBuilding.GraphBuilder;
import ProgramGraph.ProgramGraph;

public class GraphVizCodeProcessor extends CodeProcessor {

	public GraphVizCodeProcessor(String[] classes) {
		this.classes = classes;
		this.gr = new GraphVizGraphReader();
		this.g = new GraphBuilder();
		this.u = new GraphVizGenerator();
	}

	@Override
	public void process() throws IOException {
		ProgramGraph g = this.g.build(this.classes);
		String dotCode = this.gr.parse(g);
		this.u.writeFile(this.f, dotCode);
	}

}
