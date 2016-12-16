package application;

public class RecursionArgumentProcessor extends CommandLineArgumentProcessorDecorator{

	RecursionArgumentProcessor(CommandLineProcessor p) {
		super(p);
	}

	@Override
	public CodeProcessor process(String[] args) {
		for(int i = 0; i < args.length; i++){
			if(args[i].equals("-r")){
				CodeProcessor pr = this.p.process(this.removeIndex(args, i));
				pr.setNodeGenerator(new RecursiveNodeGenerator());
				return pr;
			}
		}
		return this.p.process(args);
	}

}
