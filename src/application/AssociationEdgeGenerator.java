package application;

import java.util.List;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

public class AssociationEdgeGenerator implements IEdgeGenerator {

	@Override
	public void generateEdge(ProgramGraph pg) {
		for(ClassNode node: pg.getNodes()){
			for(ClassNode other: pg.getNodes()){
				if(!node.equals(other)){
					List<FieldNode> fields = node.fields;
					for (FieldNode f: fields){
						String sig = f.signature;
	
						if(sig != null) {
							for(String s :Utilities.getGenericTypes(sig)){
								if(other.name.equals(Utilities.getClassPath(s))) {
									pg.addEdge(new AssociationEdge(other, node, true));
								}
							}
						}
	
						if((f.desc.length() > 1) && f.desc.substring(1, f.desc.length()-1).equals(other.name) ){
							pg.addEdge(new AssociationEdge(other, node));
						}
	
					}
				}
			}
		}
	}
}
