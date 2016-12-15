package application;
import org.objectweb.asm.tree.ClassNode;

public class ExtendsEdge extends Edge {
	
	public ExtendsEdge(ClassNode head, ClassNode tail){
		super(head, tail);
	}

	@Override
	public String getDescription() {
		return "extends";
	}

}
