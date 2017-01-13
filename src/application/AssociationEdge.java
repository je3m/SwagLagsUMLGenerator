package application;

import org.objectweb.asm.tree.ClassNode;

public class AssociationEdge extends IEdge {

	private String manies = "";

	public AssociationEdge(ClassNode head, ClassNode tail) {
		super(head, tail);
	}

	public AssociationEdge(ClassNode head, ClassNode tail, boolean many) {
		this(head,tail);
		if(many) {
			this.setManies();
		}
	}

	@Override
	public String getDescription() {
		return "association" + this.manies;
	}

	public void setManies(){
		this.manies = "many";
	}

}
