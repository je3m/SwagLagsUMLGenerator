package application;

import org.objectweb.asm.tree.ClassNode;

public class DependencyEdge extends Edge {

	public DependencyEdge(ClassNode head, ClassNode tail) {
		super(head, tail);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getDescription() {
		return "dependency";
	}
}
