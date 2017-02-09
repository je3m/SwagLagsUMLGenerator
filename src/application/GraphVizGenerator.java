package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import GraphBuilding.IUmlGenerator;

public class GraphVizGenerator implements IUmlGenerator {

	@Override
	public void writeFile(File f, String data) throws IOException {

		List<String> lines = Arrays.asList(data.split("\\n"));
		File dir = Paths.get("./input_output").toFile();
		if(!dir.exists()){
			dir.mkdir();
		}
		Path file = Paths.get("./input_output/temp.dot");
		Files.write(file, lines);

		ProcessBuilder process = new ProcessBuilder("C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe",
				"-Tpng",
				file.toAbsolutePath().toString(),
				"-o" + f.getAbsolutePath());
		process.start();
	}

}
