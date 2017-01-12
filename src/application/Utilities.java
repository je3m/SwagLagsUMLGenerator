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

	public static String getClassPath(String t){
		if((t == null)) {
			return null;
		}

		return t.replaceFirst("L", "").replace(";", "").replace("[", "");
	}

	public static String getClassPath(Type t){
		if((t == null)) {
			return null;
		}

		return t.getDescriptor().replaceFirst("L", "").replace(";", "").replace("[", "");
	}

	public static String[] getGenericTypes(String s){
		return s.substring(s.indexOf('<')+1, s.indexOf('>')).split(";");

	}
}
