package application;

import java.util.List;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class DependencyEdgeGenerator implements IEdgeGenerator {



	@SuppressWarnings("unchecked")
	@Override
	public void generateEdge(ProgramGraph pg) {
		for(ClassNode node: pg.getNodes()){
			for(ClassNode other: pg.getNodes()){
				List<MethodNode> methods = node.methods;

				for(MethodNode mn: methods) {
					for(Type t: Type.getArgumentTypes(mn.desc)) {
						//						System.out.println(node.name + " " + mn.name + ": " + Utilities.getClassPath(t));

						if(other.name.equals(Utilities.getClassPath(t))){
							pg.addEdge(new DependencyEdge(other, node));
						}

					}

					if(other.name.equals(Utilities.getClassPath(Type.getReturnType(mn.desc)))){
						pg.addEdge(new DependencyEdge(other, node));
					}




				}
			}
		}
	}

}
