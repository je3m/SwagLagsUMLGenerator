package application;

import java.util.List;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public interface MethodReader {
	public List<MethodNode> getMethods(ClassNode c);
}
