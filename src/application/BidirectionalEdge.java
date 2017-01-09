package application;

import org.objectweb.asm.tree.ClassNode;

public class BidirectionalEdge extends Edge {

	public BidirectionalEdge(ClassNode head, ClassNode tail) {
		super(head, tail);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "bidirectional"; //lgbtq friendly edge
	}

}
