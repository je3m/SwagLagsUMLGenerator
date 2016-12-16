package application;

public interface IGraphReader {
	public String parse(ProgramGraph g);
	public void addMethodReader(MethodReader r);
	public void addFieldReader(FieldReader r);
}
