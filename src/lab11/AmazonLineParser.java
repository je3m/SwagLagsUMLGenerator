package lab11;

public class AmazonLineParser implements ILineParser {

	@Override
	public String parse(String line) {
		String[] fields = line.split(" ");
		return fields[0] + " : " + fields[2].split(",")[0] + "\n" + fields[3] + " : " + fields[5].split(",")[0];
	}

}
