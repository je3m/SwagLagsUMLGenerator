package ProgramGraph;

public class GraphVizEdge extends IEdge {
	private String code;

	public GraphVizEdge(INode head, INode tail) {
		super(head, tail);
	}

	public void setCode(String s) {
		this.code = s;
	}

	@Override
	public String getDescription() {
		return "GraphVizEdge " + this.code;
	}

}
