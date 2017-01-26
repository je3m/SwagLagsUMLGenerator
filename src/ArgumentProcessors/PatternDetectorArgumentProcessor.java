package ArgumentProcessors;

import java.util.List;

import GraphReading.IUserGraphMutator;
import application.CodeProcessor;

public class PatternDetectorArgumentProcessor extends CommandLineArgumentProcessorDecorator{

	public PatternDetectorArgumentProcessor(CommandLineProcessor p) {
		super(p);
		this.prefix = "patterns";
	}

	@Override
	public CodeProcessor process(String[] args) {
		for(int i = 0; i < args.length; i++){
			if(this.verifyPrefix(args[i])){
				CodeProcessor c = this.p.process(this.removeIndex(args, i));
				for(String s: args[i].split("=")[1].split(",")){
					try {
						c.addGraphMutator((IUserGraphMutator)Class.forName(s)
								.getConstructor(List.class, List.class)
								.newInstance(c.getFieldReaders(), c.getMethodReaders()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return c;
			}
		}
		return this.p.process(args);
	}

}
