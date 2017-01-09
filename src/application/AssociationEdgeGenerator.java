package application;

import java.util.List;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

public class AssociationEdgeGenerator implements IEdgeGenerator {

	@Override
	public void generateEdge(ProgramGraph pg) {
		for(ClassNode node: pg.getNodes()){
			for(ClassNode other: pg.getNodes()){

				List<FieldNode> fields = node.fields;
				for (FieldNode f: fields){
					if(f.desc.substring(1, f.desc.length()-1).equals(other.name) ){
						pg.addEdge(new AssociationEdge(node, other));
					}

				}
			}
		}
	}
}
