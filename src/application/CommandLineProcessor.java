package application;

import java.util.ArrayList;

public abstract class CommandLineProcessor {
	//TODO: Demo
//	public ArrayList<CommandLineArgumentProcessorDecorator> p;
//	public void test(ArrayList<AccessLevelArgumentProcessor> args) {
//	}
	public abstract CodeProcessor process(String[] args);
}
