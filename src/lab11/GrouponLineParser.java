package lab11;

public class GrouponLineParser implements ILineParser{

	@Override
	public String parse(String line) {
		String[] fields = line.split(" ");
		return fields[0] + " : " + fields[fields.length - 1];
	}

}
