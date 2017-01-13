package application;

import java.util.ArrayList;
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
				if(!node.equals(other)){
					List<MethodNode> methods = node.methods;
					for(MethodNode mn: methods) {
	
	
						//find one to many relationships with dependencies
						if(mn.signature != null){
							String argument = mn.signature.replace("(", "").split("\\)")[0];
							String returnType = Utilities.getClassPath(mn.signature.replace("(", "").split("\\)")[1]);
	
							//parsing some jank arguments
							boolean split = true;
							ArrayList<String> args = new ArrayList<String>();
							args.add("");
							for(int i = 0; i < argument.length(); i++){
								if(argument.charAt(i) == '<'){
									split = false;
								} else if (argument.charAt(i) == '>'){
									split = true;
								} else if ((argument.charAt(i) == ';') && split){
									args.add("");
								}
	
								if (!((argument.charAt(i) == ';') && split)) {
									args.set(args.size() - 1, args.get(args.size()-1) +  argument.charAt(i));
								}
	
	
							}
	
							//checking though parameters for array lists contents
							for (String s : args){
								if(!s.equals("") && s.contains("<")){
	
									for (String sn : Utilities.getGenericTypes(s)){
	
										if(Utilities.getClassPath(sn).equals(Utilities.getClassPath(other.name))) {
											pg.addEdge(new DependencyEdge(other, node, true));
										}
									}
	
								}
	
							}
	
							if(returnType.contains("<")){
								for (String s : Utilities.getGenericTypes(returnType)) {
									if(Utilities.getClassPath(s).equals(Utilities.getClassPath(other.name))) {
										pg.addEdge(new DependencyEdge(other, node, true));
									}
								}
							}
						}
	
						for(Type t: Type.getArgumentTypes(mn.desc)) {
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
}
