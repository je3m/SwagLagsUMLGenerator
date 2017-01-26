package ProgramGraph;

public class AssociationBidirectionalEdge extends IEdge {

	//Notation is on the head
	private String manyHead = "";

	//Notation is on the tail
	private String manyTail = "";

	public AssociationBidirectionalEdge(INode head, INode tail) {
		super(head, tail);
	}

	public void setManyHead(){
		this.manyHead = "manyH";
	}

	public void setManyTail(){
		this.manyTail = "manyT";
	}

	@Override
	public String getDescription() {
		return "abidirectional" + this.manyHead + this.manyTail; //lgbtq friendly edge
	}

}
