package application;

public abstract class CodeProcessor {
	protected IGraphReader gr;
	protected IUmlGenerator u;
	protected GraphBuilder g;
	protected String[] classes;

	public abstract void process();

	public void setGenerator(IUmlGenerator u) {
		this.u = u;
	}

	public void setParser(IGraphReader gr) {
		this.gr = gr;
	}
}
