package ProgramGraph;

import org.objectweb.asm.tree.ClassNode;

public class DependencyEdge extends IEdge {

	private String manies = "";

	public DependencyEdge(ClassNode head, ClassNode tail) {
		super(head, tail);
	}

	public DependencyEdge(ClassNode head, ClassNode tail, boolean many) {
		super(head, tail);
		if(many) {
			this.manies = " many";
		}
	}

	@Override
	public String getDescription() {
		return "dependency" + this.manies;
	}
}
