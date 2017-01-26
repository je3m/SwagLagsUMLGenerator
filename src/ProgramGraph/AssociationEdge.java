package ProgramGraph;

public class AssociationEdge extends IEdge {

	private String manies = "";

	public AssociationEdge(INode head, INode tail) {
		super(head, tail);
	}

	public AssociationEdge(INode head, INode tail, boolean many) {
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
