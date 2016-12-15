package application;

public class BasicCommandLineProcessor extends CommandLineProcessor {
	@Override
	public CodeProcessor process(String[] args) {
		return new GraphVizCodeProcessor(args);
	}
}
