package GraphBuilding;

import java.io.File;
import java.io.IOException;

public interface IUmlGenerator {
	public void writeFile(File f, String data) throws IOException;
}
