package GraphBuilding;

import java.util.List;

import org.objectweb.asm.tree.FieldNode;

import ProgramGraph.AssociationEdge;
import ProgramGraph.INode;
import ProgramGraph.ProgramGraph;
import application.Utilities;

public class AssociationEdgeGenerator implements IEdgeGenerator {

	@Override
	public void generateEdge(ProgramGraph pg) {
		for(INode node: pg.getINodes()){
			for(INode other: pg.getINodes()){
				if(!node.equals(other)){
					List<FieldNode> fields = node.getClassNode().fields;
					for (FieldNode f: fields){
						String sig = f.signature;

						if(sig != null) {
							for(String s :Utilities.getGenericTypes(sig)){
								if(other.getClassNode().name.equals(Utilities.getClassPath(s))) {
									pg.addEdge(new AssociationEdge(other, node, true));
								}
							}
						}

						if((f.desc.length() > 1) && f.desc.substring(1, f.desc.length()-1).equals(other.getClassNode().name) ){
							pg.addEdge(new AssociationEdge(other, node));
						}

					}
				}
			}
		}
	}
}
