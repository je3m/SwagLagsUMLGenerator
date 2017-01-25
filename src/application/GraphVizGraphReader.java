package application;

import java.util.ArrayList;

import org.objectweb.asm.tree.ClassNode;

public class GraphVizGraphReader implements IGraphReader {
	private ArrayList<FieldReader> fieldReaders = new ArrayList<FieldReader>();
	private ArrayList<MethodReader> methodReaders = new ArrayList<MethodReader>();
	private ArrayList<IEdgeReader> edgeReaders = new ArrayList<IEdgeReader>();

	@Override
	public void addEdgeReader(IEdgeReader r){
		this.edgeReaders.add(r);
	}

	@Override
	public void addFieldReader(FieldReader r){
		this.fieldReaders.add(r);
	}

	@Override
	public void addMethodReader(MethodReader r){
		this.methodReaders.add(r);
	}


	@Override
	public String parse(ProgramGraph g) {
		String code = "digraph memes {";

		code += "rankdir=BT;\n";

		for(ClassNode c : g.getNodes()) {


		}

		for(IEdgeReader edgeReader : this.edgeReaders){
			code += edgeReader.getEdges(g.getEdges());
		}

		code += "\n}";
		return code;
	}

}
