package ProgramGraph;

import org.objectweb.asm.tree.ClassNode;

public class AssociationBidirectionalEdge extends IEdge {
	
	//Notation is on the head
	private String manyHead = "";
	
	//Notation is on the tail
	private String manyTail = "";
	
	public AssociationBidirectionalEdge(ClassNode head, ClassNode tail) {
		super(head, tail);
	}
	
	public void setManyHead(){
		manyHead = "manyH";
	}
	
	public void setManyTail(){
		manyTail = "manyT";
	}

	@Override
	public String getDescription() {
		return "abidirectional" + manyHead + manyTail; //lgbtq friendly edge
	}

}
