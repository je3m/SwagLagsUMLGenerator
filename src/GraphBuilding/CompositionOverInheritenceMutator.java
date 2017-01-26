package GraphBuilding;

import java.util.HashSet;
import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.MethodNode;

import GraphReading.IUserGraphMutator;
import ProgramGraph.GraphVizEdge;
import ProgramGraph.IEdge;
import ProgramGraph.ProgramGraph;
import application.Utilities;

public class CompositionOverInheritenceMutator implements IUserGraphMutator {

	private GraphVizEdge makeEdge(IEdge e){
		GraphVizEdge ans = new GraphVizEdge(e.getHead(), e.getTail());

		String code = "";


		code += Utilities.getClassName(e.getTail().name);
		code += " -> ";
		code += Utilities.getClassName(e.getHead().name);
		code += " [arrowhead=\"onormal\", style=\"solid\", color=\"orange\"];\n";

		ans.setCode(code);

		return ans;


	}

	@SuppressWarnings("unchecked")
	@Override
	public void mutate(ProgramGraph g) {
		HashSet<IEdge> edgesToKill = new HashSet<IEdge>();
		HashSet<IEdge> edgesToAdd = new HashSet<IEdge>();


		for (IEdge e : g.getEdges()){
			if(e.getDescription().contains("extends")){
				for(MethodNode m: (List<MethodNode>) e.getTail().methods){
					if (m.name.equals("<init>")) {
						continue;
					}
					for(MethodNode n: (List<MethodNode>) e.getHead().methods ) {
						if(n.name.equals(m.name)) {
							edgesToKill.add(e);
							edgesToAdd.add(this.makeEdge(e));
							System.out.println("Orange: " + ((m.access & Opcodes.ACC_ABSTRACT) != 0) + " " + e.getTail().name + " Meme'd on " + m.name);
						}
					}
				}
			}
		}

		for(IEdge e : edgesToKill){
			g.removeEdge(e);
		}

		for(IEdge e : edgesToAdd){
			g.addEdge(e);
		}
	}

}

