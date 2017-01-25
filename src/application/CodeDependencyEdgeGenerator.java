package application;

import java.util.List;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;

public class CodeDependencyEdgeGenerator implements IEdgeGenerator {



	@SuppressWarnings("unchecked")
	@Override
	public void generateEdge(ProgramGraph pg) {
		for(ClassNode node: pg.getNodes()){
			for(ClassNode other: pg.getNodes()){
				if(!node.equals(other)){
					List<MethodNode> methods = node.methods;
					for(MethodNode mn: methods) {
						if (mn.localVariables != null) {
							for(LocalVariableNode vn : (List<LocalVariableNode>) mn.localVariables) {
								if(other.name.equals(Utilities.getClassPath(Type.getType(vn.desc)))){
									if(!pg.getEdges().contains(new DependencyEdge(other, node, true))){
										pg.addEdge(new DependencyEdge(other, node));
									}
								}
							}
						}
					}
				}
			}
		}
	}

}
