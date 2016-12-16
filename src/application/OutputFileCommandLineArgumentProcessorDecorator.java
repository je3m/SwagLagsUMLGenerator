package application;

import java.io.File;

public class OutputFileCommandLineArgumentProcessorDecorator extends CommandLineArgumentProcessorDecorator{

	OutputFileCommandLineArgumentProcessorDecorator(CommandLineProcessor p) {
		super(p);
	}

	@Override
	public CodeProcessor process(String[] args) {
		for(int i = 0; i < args.length; i++){
			if(args[i].substring(0, 3).equals("of=")){
				CodeProcessor c = this.p.process(removeIndex(args,i));
				c.setFile(new File(args[i].substring(3)));
				return c;
			}
		}
		return this.p.process(args);
	}


}
