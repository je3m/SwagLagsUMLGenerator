package application;

import org.objectweb.asm.Type;

public class Utilities {
	public static String getClassName(String s){
		return s.substring(s.lastIndexOf('/') + 1);
	}

	public static String getClassName(Type t) {
		String[] temp = t.getClassName().split("(\\.|\\/)");

		return temp[temp.length - 1];

	}

	public static String getClassPath(Type t){
		if((t == null)) {
			return null;
		}

		return t.getDescriptor().replaceFirst("L", "").replace(";", "").replace("[", "");
	}
}
