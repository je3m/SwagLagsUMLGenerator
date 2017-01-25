package application;

import java.io.IOException;

import ArgumentProcessors.AccessLevelArgumentProcessor;
import ArgumentProcessors.BasicCommandLineProcessor;
import ArgumentProcessors.OutputFileCommandLineArgumentProcessorDecorator;
import ArgumentProcessors.RecursionArgumentProcessor;
import ArgumentProcessors.SettingsFileCommandArgumentProcessor;

public class Application {
	public static void main(String[] args) throws IOException{
		BasicCommandLineProcessor c = new BasicCommandLineProcessor();
		OutputFileCommandLineArgumentProcessorDecorator o = new OutputFileCommandLineArgumentProcessorDecorator(c);
		RecursionArgumentProcessor r = new RecursionArgumentProcessor(o);
		AccessLevelArgumentProcessor a = new AccessLevelArgumentProcessor(r);

		SettingsFileCommandArgumentProcessor s = new SettingsFileCommandArgumentProcessor(a);

		CodeProcessor p = s.process(args);

		p.process();
	}
}
