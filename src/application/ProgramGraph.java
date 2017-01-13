package application;
import java.util.ArrayList;
import java.util.HashSet;

import org.objectweb.asm.tree.ClassNode;

public class ProgramGraph {
	private ArrayList<ClassNode> c;
	private HashSet<IEdge> e;


	public ProgramGraph(){
		this.c = new ArrayList<ClassNode>();
		this.e = new HashSet<IEdge>();
	}

	public void addEdge(IEdge e){
		this.e.add(e);
	}

	public void addNode(ClassNode n){
		this.c.add(n);
	}

	public ArrayList<IEdge> getEdges(){
		ArrayList<IEdge> memes = new ArrayList<>(this.e);
		return memes;
	}

	public ArrayList<ClassNode> getNodes(){
		return this.c;
	}

	public void removeEdge(IEdge e){
		this.e.remove(e);
	}
}
