package application;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import jdk.internal.org.objectweb.asm.Opcodes;

public class GraphVizPublicMethodReader implements MethodReader{
	@Override
	public List<MethodNode> getMethods(ClassNode c) {
		List<MethodNode> methods = c.methods;
		List<MethodNode> returnMethods = new ArrayList<MethodNode>();
		for(MethodNode method: methods){
			if((method.access & Opcodes.ACC_PUBLIC) > 0){
				returnMethods.add(method);
			}
		}
		return returnMethods;
	}
}
