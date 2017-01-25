package ArgumentProcessors;

import java.io.File;

import application.CodeProcessor;

public class OutputFileCommandLineArgumentProcessorDecorator extends CommandLineArgumentProcessorDecorator{

	public OutputFileCommandLineArgumentProcessorDecorator(CommandLineProcessor p) {
		super(p);
		this.prefix="of";
	}

	@Override
	public CodeProcessor process(String[] args) {
		for(int i = 0; i < args.length; i++){
			if(this.verifyPrefix(args[i])){
				CodeProcessor c = this.p.process(this.removeIndex(args,i));
				c.setFile(new File(args[i].split("=")[1]));
				return c;
			}
		}
		return this.p.process(args);
	}


}
