package application;

import java.io.File;
import java.util.ArrayList;

public class OutputFileCommandLineArgumentProcessorDecorator extends CommandLineArgumentProcessorDecorator{

	OutputFileCommandLineArgumentProcessorDecorator(CommandLineProcessor p) {
		super(p);
	}

	@Override
	public CodeProcessor process(String[] args) {
		for(int i = 0; i < args.length; i++){
			if(args[i].substring(0, 3).equals("of=")){
				CodeProcessor c = p.process(removeIndex(args,i));
				c.setFile(new File(args[i].substring(3)));
				return c;
			}
		}
		return p.process(args);
	}
	
	private String[] removeIndex(String[] args, int index){
		String[] newArgs = new String[args.length-1];
		int count = 0;
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
