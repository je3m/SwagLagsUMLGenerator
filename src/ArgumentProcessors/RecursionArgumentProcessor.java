package ArgumentProcessors;

import GraphBuilding.RecursiveNodeGenerator;
import application.CodeProcessor;

public class RecursionArgumentProcessor extends CommandLineArgumentProcessorDecorator{

	public RecursionArgumentProcessor(CommandLineProcessor p) {
		super(p);
		this.prefix = "recursive";
	}

	@Override
	public CodeProcessor process(String[] args) {
		for(int i = 0; i < args.length; i++){
			if(this.verifyPrefix(args[i]) && args[i].split("=")[1].equals("true")){
				RecursiveNodeGenerator gen = new RecursiveNodeGenerator();
				int j;
				for(j = 0; j < args.length; j++){
					if(args[j].split("=")[0].equals("blacklist")){
						String[] blacks = args[j].split("=")[1].split(",");
						for(String black: blacks){
							gen.addBlackListed(black.replaceAll("\\.","/"));
						}
						break;
					}
				}
				if(j == args.length){
					this.removeIndex(args, i);
				} else if(i > j){
					args = this.removeIndex(this.removeIndex(args, i), j);
				} else {
					args = this.removeIndex(this.removeIndex(args, j), i);
				}
				CodeProcessor pr = this.p.process(args);
				pr.setNodeGenerator(gen);
				return pr;
			}
		}
		return this.p.process(args);
	}

}
