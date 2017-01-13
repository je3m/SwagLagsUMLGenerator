package application;

public class BasicCommandLineProcessor extends CommandLineProcessor {
	@Override
	public CodeProcessor process(String[] args) {
		GraphVizCodeProcessor tmp = new GraphVizCodeProcessor(args);
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

		tmp.addEdgeChecker(new AssociationDependencyChecker());
		tmp.addEdgeChecker(new BidirectionalEdgeChecker());
		tmp.addEdgeChecker(new DuplicateDependencyEdgeChecker());

		return tmp;
	}
}
