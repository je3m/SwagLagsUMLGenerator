package application;

public abstract class CommandLineProcessor {
	//	public ArrayList<CommandLineArgumentProcessorDecorator> p;
	public abstract CodeProcessor process(String[] args);
}
