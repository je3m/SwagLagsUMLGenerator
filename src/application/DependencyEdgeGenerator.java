package application;

import java.util.List;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class DependencyEdgeGenerator implements IEdgeGenerator {



	@SuppressWarnings("unchecked")
	@Override
	public void generateEdge(ProgramGraph pg) {
		for(ClassNode node: pg.getNodes()){
			for(ClassNode other: pg.getNodes()){
				List<MethodNode> methods = node.methods;
				for(MethodNode mn: methods) {
//					System.out.println(mn.desc);
					for(Type t: Type.getArgumentTypes(mn.desc)) {
//						try{
//							System.out.println(node.name + " " + mn.name + ": ");
//						} catch (Exception e){
//							
//						}
						if(other.name.equals(Utilities.getClassPath(t))){
							pg.addEdge(new DependencyEdge(other, node));
						}
					}
//					System.out.println(mn.name);
//					System.out.println(other.name + ":" + node.name);
//					System.out.println(Utilities.getClassPath(Type.getReturnType(mn.desc)));
					if(other.name.equals(Utilities.getClassPath(Type.getReturnType(mn.desc)))){
//						System.out.println(other.name + ":" + node.name);
						pg.addEdge(new DependencyEdge(other, node));
					}
//					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				}
			}
		}
	}

}
