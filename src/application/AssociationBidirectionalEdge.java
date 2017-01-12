package application;

import org.objectweb.asm.tree.ClassNode;

public class AssociationBidirectionalEdge extends Edge {

	public AssociationBidirectionalEdge(ClassNode head, ClassNode tail) {
		super(head, tail);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "abidirectional"; //lgbtq friendly edge
	}

}
