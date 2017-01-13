package application;

import org.objectweb.asm.tree.ClassNode;

public class ImplementsEdge extends IEdge {

	public ImplementsEdge(ClassNode head, ClassNode tail) {
		super(head, tail);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getDescription() {
		return "implements";
	}

}
