package ArgumentProcessors;

import application.CodeProcessor;

public abstract class CommandLineProcessor {
	protected String prefix;

	public abstract CodeProcessor process(String[] args);

	public boolean verifyPrefix(String arg){
		return arg.split("=")[0].equals(this.prefix);
	}
}
