package ArgumentProcessors;

import java.util.Stack;

import GraphReading.GraphVizPrivateFieldReader;
import GraphReading.GraphVizPrivateMethodReader;
import GraphReading.GraphVizProtectedFieldReader;
import GraphReading.GraphVizProtectedMethodReader;
import GraphReading.GraphVizPublicFieldReader;
import GraphReading.GraphVizPublicMethodReader;
import application.CodeProcessor;

public class AccessLevelArgumentProcessor extends CommandLineArgumentProcessorDecorator{
	public AccessLevelArgumentProcessor(CommandLineProcessor p) {
		super(p);
		this.prefix = "access";
	}

	@Override
	public CodeProcessor process(String[] args) {
		boolean isPublic = false;
		boolean isPrivate = false;
		boolean isProtected = false;
		boolean found = false;
		Stack<Integer> indexs = new Stack<Integer>();
		for(int i = 0; i < args.length; i++){
			if(this.verifyPrefix(args[i])){
				switch(args[i].split("=")[1]){
				case "private":
					isPrivate = true;
				case "protected":
					isProtected = true;
				case "public":
					isPublic = true;
					found = true;
					indexs.push(i);
				}
			}
		}
		if(!found){
			isPublic = true;
			isPrivate = true;
			isProtected = true;
		}
		while(!indexs.isEmpty()){
			args = this.removeIndex(args, indexs.pop());
		}
		CodeProcessor c = this.p.process(args);
		if(isPrivate){
			c.addFieldReader(new GraphVizPrivateFieldReader());
			c.addMethodReader(new GraphVizPrivateMethodReader());
		}
		if(isProtected){
			c.addFieldReader(new GraphVizProtectedFieldReader());
			c.addMethodReader(new GraphVizProtectedMethodReader());
		}
		if(isPublic){
			c.addFieldReader(new GraphVizPublicFieldReader());
			c.addMethodReader(new GraphVizPublicMethodReader());
		}

		return c;
	}
}
