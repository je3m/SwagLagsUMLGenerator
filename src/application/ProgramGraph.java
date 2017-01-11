package application;
import java.util.ArrayList;
import java.util.HashSet;

import org.objectweb.asm.tree.ClassNode;

public class ProgramGraph {
	private ArrayList<ClassNode> c;
	private HashSet<Edge> e;

	public ProgramGraph(){
		this.c = new ArrayList<ClassNode>();
		this.e = new HashSet<Edge>();
	}

	public void addEdge(Edge e){
		this.e.add(e);
	}

	public void addNode(ClassNode n){
		this.c.add(n);
	}

	public ArrayList<Edge> getEdges(){
		ArrayList<Edge> memes = new ArrayList<>(this.e);
		return memes;
	}

	public ArrayList<ClassNode> getNodes(){
		return this.c;
	}


	public void removeEdge(Edge e){
		this.e.remove(e);
	}
}
