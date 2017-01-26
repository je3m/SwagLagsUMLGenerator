package ProgramGraph;

public class ExtendsEdge extends IEdge {

	public ExtendsEdge(INode head, INode tail){
		super(head, tail);
	}

	@Override
	public String getDescription() {
		return "extends";
	}

}
