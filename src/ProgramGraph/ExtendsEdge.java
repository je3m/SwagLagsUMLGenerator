package ProgramGraph;
import org.objectweb.asm.tree.ClassNode;

public class ExtendsEdge extends IEdge {

	public ExtendsEdge(ClassNode head, ClassNode tail){
		super(head, tail);
	}

	@Override
	public String getDescription() {
		return "extends";
	}

}
