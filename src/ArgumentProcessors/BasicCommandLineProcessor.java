package ArgumentProcessors;

import GraphBuilding.AssociationDependencyChecker;
import GraphBuilding.AssociationEdgeGenerator;
import GraphBuilding.BidirectionalEdgeChecker;
import GraphBuilding.CodeDependencyEdgeGenerator;
import GraphBuilding.DependencyEdgeGenerator;
import GraphBuilding.DuplicateDependencyEdgeChecker;
import GraphBuilding.ExtendsEdgeGenerator;
import GraphBuilding.ImplementsEdgeGenerator;
import GraphReading.AssociationBidirectionalEdgeReader;
import GraphReading.AssociationEdgeReader;
import GraphReading.DependencyBidirectionalEdgeReader;
import GraphReading.DependencyEdgeReader;
import GraphReading.ExtendsEdgeReader;
import GraphReading.ImplementsEdgeReader;
import GraphReading.NormalNodeReader;
import application.CodeProcessor;
import application.GraphVizCodeProcessor;

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
