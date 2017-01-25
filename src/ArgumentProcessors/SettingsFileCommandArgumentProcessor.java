package ArgumentProcessors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import application.CodeProcessor;

public class SettingsFileCommandArgumentProcessor extends CommandLineArgumentProcessorDecorator{

	public SettingsFileCommandArgumentProcessor(CommandLineProcessor p) {
		super(p);
		this.prefix = "cfg";
	}

	@Override
	public CodeProcessor process(String[] args) {
		for(int i = 0; i < args.length; i++){
			if(this.verifyPrefix(args[i])){
				String[] newArgs = this.processArgs(args[i].split("=")[1], this.removeIndex(args,i));
				CodeProcessor c = this.p.process(newArgs);
				return c;
			}
		}
		String[] newArgs = this.processArgs("default.cfg",args);
		CodeProcessor c = this.p.process(newArgs);
		return c;
	}

	private String[] processArgs(String fileName, String[] args){
		File f = new File(fileName);
		try {
			BufferedReader bf = new BufferedReader(new FileReader(f));
			HashMap<String, String> realArgs = new HashMap<String, String>();
			for(String a: args){
				String pref = a.split("=")[0];
				realArgs.put(pref, a);
			}
			String arg = bf.readLine();
			while(arg != null){
				String pref = arg.split("=")[0];
				if(!realArgs.containsKey(pref)){
					realArgs.put(pref, arg);
				}
				arg = bf.readLine();
			}
			return realArgs.values().toArray(new String[0]);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Config File " + fileName + " not found.");
		}
		return null;
	}

}
