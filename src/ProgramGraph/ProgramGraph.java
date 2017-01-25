package ProgramGraph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.objectweb.asm.tree.ClassNode;

public class ProgramGraph {
	private ArrayList<INode> c;
	private HashSet<IEdge> e;


	public ProgramGraph(){
		this.c = new ArrayList<INode>();
		this.e = new HashSet<IEdge>();
	}

	public void addEdge(IEdge e){
		this.e.add(e);
	}

	public void addNode(ClassNode n) {
		this.c.add(new NormalNode(n));
	}

	public void addNode(INode n) {
		this.c.add(n);
	}

	public ArrayList<IEdge> getEdges(){
		ArrayList<IEdge> memes = new ArrayList<>(this.e);
		return memes;
	}

	public ArrayList<ClassNode> getNodes(){
		return this.c.stream().map((x)->x.getClassNode()).collect(Collectors.toCollection(ArrayList::new));
	}

	public ArrayList<INode> getINodes() {
		return this.c;
	}

	public void removeEdge(IEdge e){
		this.e.remove(e);
	}
}
