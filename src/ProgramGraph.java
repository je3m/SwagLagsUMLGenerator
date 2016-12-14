import java.util.ArrayList;

import jdk.internal.org.objectweb.asm.tree.ClassNode;

public class ProgramGraph {
	private ArrayList<ClassNode> c;
	private ArrayList<IEdge> e;
	
	public ProgramGraph(){
		c = new ArrayList<ClassNode>();
		e = new ArrayList<IEdge>();
	}
	
	public ArrayList<ClassNode> getNodes(){
		return this.c;
	}
	
	public ArrayList<IEdge> getEdges(){
		return this.e;
	}
	
	public void addNode(ClassNode n){
		this.c.add(n);
	}
	
	public void addEdge(IEdge e){
		this.e.add(e);
	}
}
