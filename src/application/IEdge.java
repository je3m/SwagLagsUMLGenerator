package application;
import org.objectweb.asm.tree.ClassNode;

public abstract class IEdge {
	private ClassNode head;
	private ClassNode tail;
	
	public IEdge(ClassNode head, ClassNode tail){
		this.head = head;
		this.tail = tail;
	}
	
	public ClassNode getHead(){
		return this.head;
	}
	public ClassNode getTail(){
		return this.tail;
	}
	public abstract String getDescription();
}
