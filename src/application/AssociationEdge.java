package application;

import org.objectweb.asm.tree.ClassNode;

public class AssociationEdge extends Edge {

	public AssociationEdge(ClassNode head, ClassNode tail) {
		super(head, tail);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "association";
	}

}
