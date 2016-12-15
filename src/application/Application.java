package application;

import java.io.IOException;

public class Application {
	public static void main(String[] args) throws IOException{
		CommandLineProcessor c = new BasicCommandLineProcessor();
		OutputFileCommandLineArgumentProcessorDecorator o = new OutputFileCommandLineArgumentProcessorDecorator(c);
		CodeProcessor p = o.process(args);
		p.process();
	}
}
