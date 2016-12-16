package application;

import java.io.File;
import java.io.IOException;

public abstract class CodeProcessor {
	protected IGraphReader gr;
	protected IUmlGenerator u;
	protected GraphBuilder g;
	protected File f;
	protected String[] classes;

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
