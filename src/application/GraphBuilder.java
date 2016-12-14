package application;
import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import com.sun.javafx.sg.prism.NodeEffectInput;


public class GraphBuilder implements IGraphBuilder{

	@Override
	public ProgramGraph build(String[] classes) throws IOException {
		ProgramGraph pg = new ProgramGraph();

		for(String className : classes){
			this.readClass(className, pg);
		}
		
		for(ClassNode node: pg.getNodes()){
			for(ClassNode other: pg.getNodes()){
				if(node.superName.equals(other.name)){
					IEdge e = new ExtendsEdge(node, other);
					pg.addEdge(e);
				}
			}
		}

		return pg;
	}

	private void readClass(String s, ProgramGraph pg) throws IOException {
		ClassReader reader = new ClassReader(s);
		ClassNode node = new ClassNode();
		reader.accept(node, ClassReader.EXPAND_FRAMES);

		pg.addNode(node);
	}
}
