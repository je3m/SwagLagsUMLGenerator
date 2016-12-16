package application;

import java.io.IOException;

public class Application {
	public static void main(String[] args) throws IOException{
		CommandLineProcessor c = new BasicCommandLineProcessor();
		OutputFileCommandLineArgumentProcessorDecorator o = new OutputFileCommandLineArgumentProcessorDecorator(c);
		RecursionArgumentProcessor r = new RecursionArgumentProcessor(o);

		CodeProcessor p = r.process(args);
		p.process();
	}
}
