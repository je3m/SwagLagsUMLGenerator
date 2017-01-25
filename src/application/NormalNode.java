package application;

import org.objectweb.asm.tree.ClassNode;

public class NormalNode implements INode {

	private ClassNode c;

	public NormalNode(ClassNode c){
		this.c = c;
	}
	@Override
	public ClassNode getClassNode() {
		return this.c;
	}

	@Override
	public String getDescription() {
		return "normal";
	}

}
