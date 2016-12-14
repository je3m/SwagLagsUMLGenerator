package application;
import java.util.ArrayList;

import org.objectweb.asm.tree.ClassNode;

public class ProgramGraph {
	private ArrayList<ClassNode> c;
	private ArrayList<IEdge> e;

	public ProgramGraph(){
		this.c = new ArrayList<ClassNode>();
		this.e = new ArrayList<IEdge>();
	}

	public void addEdge(IEdge e){
		this.e.add(e);
	}

	public void addNode(ClassNode n){
		this.c.add(n);
	}

	public ArrayList<IEdge> getEdges(){
		return this.e;
	}

	public ArrayList<ClassNode> getNodes(){
		return this.c;
	}
}
