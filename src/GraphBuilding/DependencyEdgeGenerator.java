package GraphBuilding;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.MethodNode;

import ProgramGraph.DependencyEdge;
import ProgramGraph.INode;
import ProgramGraph.ProgramGraph;
import application.Utilities;

public class DependencyEdgeGenerator implements IEdgeGenerator {



	@SuppressWarnings("unchecked")
	@Override
	public void generateEdge(ProgramGraph pg) {
		for(INode node: pg.getINodes()){
			for(INode other: pg.getINodes()){
				if(!node.equals(other)){
					List<MethodNode> methods = node.getClassNode().methods;
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
										if(Utilities.getClassPath(sn).equals(other.getClassNode().name)) {
											pg.addEdge(new DependencyEdge(other, node, true));
										}
									}
								}
							}

							if(returnType.contains("<")){
								for (String s : Utilities.getGenericTypes(returnType)) {
									if(Utilities.getClassPath(s).equals(other.getClassNode().name)) {
										pg.addEdge(new DependencyEdge(other, node, true));
									}
								}
							}
						}

						for(Type t: Type.getArgumentTypes(mn.desc)) {
							if(other.getClassNode().name.equals(Utilities.getClassPath(t))){
								pg.addEdge(new DependencyEdge(other, node));
							}
						}

						if(other.getClassNode().name.equals(Utilities.getClassPath(Type.getReturnType(mn.desc)))){
							pg.addEdge(new DependencyEdge(other, node));
						}
					}
				}
			}
		}
	}
}
