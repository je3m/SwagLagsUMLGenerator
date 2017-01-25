package ArgumentProcessors;

public abstract class CommandLineArgumentProcessorDecorator extends CommandLineProcessor {
	protected CommandLineProcessor p;
	CommandLineArgumentProcessorDecorator(CommandLineProcessor p){
		this.p = p;
	}
	protected String[] removeIndex(String[] args, int index){
		String[] newArgs = new String[args.length-1];
		for(int i = 0; i < args.length; i++){
			if(i > index){
				newArgs[i - 1] = args[i];
			} else if (i < index) {
				newArgs[i] = args[i];
			}
		}
		return newArgs;
	}
}
