package ProgramGraph;

import org.objectweb.asm.tree.ClassNode;

public class GraphVizEdge extends IEdge {
	private String code;

	public GraphVizEdge(ClassNode head, ClassNode tail) {
		super(head, tail);
	}

	public void setCode(String s) {
		this.code = s;
	}

	@Override
	public String getDescription() {
		return "GraphVizEdge " + this.code;
	}

}
