package ProgramGraph;

public class ImplementsEdge extends IEdge {

	public ImplementsEdge(INode head, INode tail) {
		super(head, tail);
	}

	@Override
	public String getDescription() {
		return "implements";
	}

}
