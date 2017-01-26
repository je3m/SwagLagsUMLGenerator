package ProgramGraph;
import org.objectweb.asm.tree.ClassNode;

public abstract class IEdge {
	private INode head;
	private INode tail;

	public IEdge(INode head, INode tail){
		this.head = head;
		this.tail = tail;
	}

	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}

	public abstract String getDescription();
	public ClassNode getHead(){
		return this.head.getClassNode();
	}

	public INode getIHead(){
		return this.head;
	}

	public ClassNode getTail(){
		return this.tail.getClassNode();
	}

	public INode getITail(){
		return this.tail;
	}

	@Override
	public int hashCode() {
		return (this.head.getClassNode().name +
				":" +
				this.tail.getClassNode().name +
				":" +
				this.getDescription()).hashCode();
	}
}
