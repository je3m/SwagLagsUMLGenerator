package application;

import java.util.ArrayList;
import java.util.List;

import GraphReading.IEdgeReader;
import GraphReading.IGraphReader;
import GraphReading.INodeReader;
import GraphReading.MethodReader;
import ProgramGraph.INode;
import ProgramGraph.ProgramGraph;

public class GraphVizGraphReader implements IGraphReader {
	private ArrayList<FieldReader> fieldReaders = new ArrayList<FieldReader>();
	private ArrayList<MethodReader> methodReaders = new ArrayList<MethodReader>();
	private ArrayList<IEdgeReader> edgeReaders = new ArrayList<IEdgeReader>();
	private ArrayList<INodeReader> nodeReaders = new ArrayList<INodeReader>();

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
	public void addNodeReader(INodeReader r) {
		this.nodeReaders.add(r);
	}

	@Override
	public String parse(ProgramGraph g) {
		String code = "digraph memes {";

		code += "rankdir=BT;\n";
		for(INode c: g.getINodes()) {
			for(INodeReader ir : this.nodeReaders){
				code += ir.getCode(c, this.fieldReaders, this.methodReaders);
			}
		}

		for(IEdgeReader edgeReader : this.edgeReaders){
			code += edgeReader.getEdges(g.getEdges());
		}

		code += "\n}";
		return code;
	}

	@Override
	public List<MethodReader> getMethodReaders() {
		return this.methodReaders;
	}

	@Override
	public List<FieldReader> getFieldReaders() {
		return this.fieldReaders;
	}
}
