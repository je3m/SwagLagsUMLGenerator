package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

public class AccessLevelArgumentProcessor extends CommandLineArgumentProcessorDecorator{
	AccessLevelArgumentProcessor(CommandLineProcessor p) {
		super(p);
	}

	@Override
	public CodeProcessor process(String[] args) {
		boolean isPublic = false;
		boolean isPrivate = false;
		boolean isProtected = false;
		boolean found = false;
		Stack<Integer> indexs = new Stack<Integer>();
		for(int i = 0; i < args.length; i++){
			switch(args[i]){
				case "-private":
					isPrivate = true;
				case "-protected":
					isProtected = true;
				case "-public":
					isPublic = true;
					found = true;
					indexs.push(i);
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
			c.addFieldReader(new GraphVisPrivateFieldReader());
			c.addMethodReader(new GraphVisPrivateMethodReader());
		}
		if(isProtected){
			c.addFieldReader(new GraphVisProtectedFieldReader());
			c.addMethodReader(new GraphVisProtectedMethodReader());
		}
		if(isPublic){
			c.addFieldReader(new GraphVisPublicFieldReader());
			c.addMethodReader(new GraphVisPublicMethodReader());
		}
		
		return c;
	}
}
