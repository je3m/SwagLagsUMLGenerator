package ProgramGraph;

public class DependencyEdge extends IEdge {

	private String manies = "";

	public DependencyEdge(INode head, INode tail) {
		super(head, tail);
	}

	public DependencyEdge(INode head, INode tail, boolean many) {
		super(head, tail);
		if(many) {
			this.manies = " many";
		}
	}

	@Override
	public String getDescription() {
		return "dependency" + this.manies;
	}
}
