package application;
import org.objectweb.asm.tree.ClassNode;

public abstract class Edge {
	private ClassNode head;
	private ClassNode tail;

	public Edge(ClassNode head, ClassNode tail){
		this.head = head;
		this.tail = tail;
	}

	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}
	public abstract String getDescription();

	public ClassNode getHead(){
		return this.head;
	}

	public ClassNode getTail(){
		return this.tail;
	}

	@Override
	public int hashCode() {
		return (this.head.name +
				":" +
				this.tail.name +
				":" +
				this.getDescription()).hashCode();
	}
}
