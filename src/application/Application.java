package application;

import java.io.IOException;

public class Application {
	public static void main(String[] args) throws IOException{
		BasicCommandLineProcessor c = new BasicCommandLineProcessor();
		OutputFileCommandLineArgumentProcessorDecorator o = new OutputFileCommandLineArgumentProcessorDecorator(c);
		RecursionArgumentProcessor r = new RecursionArgumentProcessor(o);
		AccessLevelArgumentProcessor a = new AccessLevelArgumentProcessor(r);

		CodeProcessor p = a.process(args);
		
		p.process();
	}
}
