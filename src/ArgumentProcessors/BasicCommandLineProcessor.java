package ArgumentProcessors;

import application.CodeProcessor;
import application.GraphVizCodeProcessor;

public class BasicCommandLineProcessor extends CommandLineProcessor {
	@Override
	public CodeProcessor process(String[] args) {
		GraphVizCodeProcessor tmp = new GraphVizCodeProcessor(args[0].split("=")[1].split(","));
		return tmp;
	}
}
