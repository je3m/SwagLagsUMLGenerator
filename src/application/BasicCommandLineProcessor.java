package application;

public class BasicCommandLineProcessor extends CommandLineProcessor {
	@Override
	public CodeProcessor process(String[] args) {
		GraphVizCodeProcessor tmp = new GraphVizCodeProcessor(args);
		tmp.addEdgeGenerator(new ExtendsEdgeGenerator());
		tmp.addEdgeGenerator(new ImplementsEdgeGenerator());

		return tmp;
	}
}
