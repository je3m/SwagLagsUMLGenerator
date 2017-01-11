package application;

import org.objectweb.asm.tree.ClassNode;

public class DependencyEdge extends Edge {

	public DependencyEdge(ClassNode head, ClassNode tail) {
		super(head, tail);
	}

	@Override
	public String getDescription() {
		return "dependency";
	}
}
