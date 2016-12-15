package application;

public abstract class CommandLineArgumentProcessorDecorator extends CommandLineProcessor {
	protected CommandLineProcessor p;
	CommandLineArgumentProcessorDecorator(CommandLineProcessor p){
		this.p = p;
	}
}
