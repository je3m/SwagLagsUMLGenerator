package application;

import org.objectweb.asm.tree.ClassNode;

public class DependencyBidirectionalEdge extends Edge {

	public DependencyBidirectionalEdge(ClassNode head, ClassNode tail) {
		super(head, tail);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "dbidirectional"; //lgbtq friendly edge
	}

}
