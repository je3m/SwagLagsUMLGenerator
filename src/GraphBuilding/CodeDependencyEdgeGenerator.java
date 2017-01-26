package GraphBuilding;

import java.util.List;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;

import ProgramGraph.DependencyEdge;
import ProgramGraph.INode;
import ProgramGraph.ProgramGraph;
import application.Utilities;

public class CodeDependencyEdgeGenerator implements IEdgeGenerator {



	@SuppressWarnings("unchecked")
	@Override
	public void generateEdge(ProgramGraph pg) {
		for(INode node: pg.getINodes()){
			for(INode other: pg.getINodes()){
				if(!node.equals(other)){
					List<MethodNode> methods = node.getClassNode().methods;
					for(MethodNode mn: methods) {
						if (mn.localVariables != null) {
							for(LocalVariableNode vn : (List<LocalVariableNode>) mn.localVariables) {
								if(other.getClassNode().name.equals(Utilities.getClassPath(Type.getType(vn.desc)))){
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
