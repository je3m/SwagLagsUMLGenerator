package application;

import org.objectweb.asm.tree.ClassNode;

public interface INode {
	public ClassNode getClassNode();
	public String getDescription();
}
