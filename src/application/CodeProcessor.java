package application;

import java.io.File;
import java.io.IOException;

public abstract class CodeProcessor {
	protected IGraphReader gr;
	protected IUmlGenerator u;
	protected IGraphBuilder g;
	protected File f;
	protected String[] classes;

	public void addEdgeChecker(IEdgeChecker ec){
		this.g.addEdgeChecker(ec);
	}

	public void addEdgeGenerator(IEdgeGenerator gen){
		this.g.addEdgeGenerator(gen);
	}

	public void addEdgeReader(IEdgeReader r){
		this.gr.addEdgeReader(r);
	}
	public void addFieldReader(FieldReader r){
		this.gr.addFieldReader(r);
	}
	public void addMethodReader(MethodReader r){
		this.gr.addMethodReader(r);
	}

	public abstract void process() throws IOException;

	public void setFile(File f) {
		this.f = f;
	}

	public void setGenerator(IUmlGenerator u) {
		this.u = u;
	}

	public void setNodeGenerator(INodeGenerator n){
		this.g.setNodeGenerator(n);
	}
	public void setParser(IGraphReader gr) {
		this.gr = gr;
	}

}
