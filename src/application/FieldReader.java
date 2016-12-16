package application;

import java.util.List;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

public interface FieldReader {
	public List<FieldNode> getFields(ClassNode c);
}
