package ArgumentProcessors;

import application.AssociationBidirectionalEdgeReader;
import application.AssociationDependencyChecker;
import application.AssociationEdgeGenerator;
import application.AssociationEdgeReader;
import application.BidirectionalEdgeChecker;
import application.CodeDependencyEdgeGenerator;
import application.CodeProcessor;
import application.DependencyBidirectionalEdgeReader;
import application.DependencyEdgeGenerator;
import application.DependencyEdgeReader;
import application.DuplicateDependencyEdgeChecker;
import application.ExtendsEdgeGenerator;
import application.ExtendsEdgeReader;
import application.GraphVizCodeProcessor;
import application.ImplementsEdgeGenerator;
import application.ImplementsEdgeReader;
import application.NormalNodeReader;

public class BasicCommandLineProcessor extends CommandLineProcessor {
	@Override
	public CodeProcessor process(String[] args) {
		GraphVizCodeProcessor tmp = new GraphVizCodeProcessor(args[0].split("=")[1].split(","));
		tmp.addEdgeGenerator(new ExtendsEdgeGenerator());
		tmp.addEdgeGenerator(new ImplementsEdgeGenerator());
		tmp.addEdgeGenerator(new AssociationEdgeGenerator());
		tmp.addEdgeGenerator(new DependencyEdgeGenerator());
		tmp.addEdgeGenerator(new CodeDependencyEdgeGenerator());

		tmp.addEdgeReader(new ExtendsEdgeReader());
		tmp.addEdgeReader(new ImplementsEdgeReader());
		tmp.addEdgeReader(new AssociationEdgeReader());
		tmp.addEdgeReader(new DependencyEdgeReader());
		tmp.addEdgeReader(new AssociationBidirectionalEdgeReader());
		tmp.addEdgeReader(new DependencyBidirectionalEdgeReader());

		tmp.addNodeReader(new NormalNodeReader());

		tmp.addEdgeChecker(new AssociationDependencyChecker());
		tmp.addEdgeChecker(new BidirectionalEdgeChecker());
		tmp.addEdgeChecker(new DuplicateDependencyEdgeChecker());

		return tmp;
	}
}
