package ProgramGraph;

import org.objectweb.asm.tree.ClassNode;

public class GraphVizNode implements INode {
	private ClassNode cn;
	private String code;

	public GraphVizNode(ClassNode cn) {
		this.cn = cn;
	}

	public void setCode(String s) {
		this.code = s;
	}

	@Override
	public ClassNode getClassNode() {
		return this.cn;
	}

	@Override
	public String getDescription() {
		return "GraphVizNode " + this.code;
	}

}
