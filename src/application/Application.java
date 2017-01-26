package application;

import java.io.IOException;

import ArgumentProcessors.AccessLevelArgumentProcessor;
import ArgumentProcessors.BasicCommandLineProcessor;
import ArgumentProcessors.OutputFileCommandLineArgumentProcessorDecorator;
import ArgumentProcessors.PatternDetectorArgumentProcessor;
import ArgumentProcessors.RecursionArgumentProcessor;
import ArgumentProcessors.SettingsFileCommandArgumentProcessor;
import GraphBuilding.AssociationDependencyChecker;
import GraphBuilding.AssociationEdgeGenerator;
import GraphBuilding.BidirectionalEdgeChecker;
import GraphBuilding.CodeDependencyEdgeGenerator;
import GraphBuilding.DependencyEdgeGenerator;
import GraphBuilding.DuplicateDependencyEdgeChecker;
import GraphBuilding.ExtendsEdgeGenerator;
import GraphBuilding.ImplementsEdgeGenerator;
import GraphReading.AssociationBidirectionalEdgeReader;
import GraphReading.AssociationEdgeReader;
import GraphReading.DependencyBidirectionalEdgeReader;
import GraphReading.DependencyEdgeReader;
import GraphReading.ExtendsEdgeReader;
import GraphReading.GraphVizEdgeReader;
import GraphReading.GraphVizNodeReader;
import GraphReading.ImplementsEdgeReader;
import GraphReading.NormalNodeReader;

public class Application {
	public static void main(String[] args) throws IOException{
		BasicCommandLineProcessor c = new BasicCommandLineProcessor();
		OutputFileCommandLineArgumentProcessorDecorator o = new OutputFileCommandLineArgumentProcessorDecorator(c);
		RecursionArgumentProcessor r = new RecursionArgumentProcessor(o);
		AccessLevelArgumentProcessor a = new AccessLevelArgumentProcessor(r);
		PatternDetectorArgumentProcessor p = new PatternDetectorArgumentProcessor(a);

		SettingsFileCommandArgumentProcessor s = new SettingsFileCommandArgumentProcessor(p);

		CodeProcessor tmp = s.process(args);

		tmp.addEdgeGenerator(new ExtendsEdgeGenerator());
		tmp.addEdgeGenerator(new ImplementsEdgeGenerator());
		tmp.addEdgeGenerator(new AssociationEdgeGenerator());
		tmp.addEdgeGenerator(new DependencyEdgeGenerator());
		tmp.addEdgeGenerator(new CodeDependencyEdgeGenerator());

		tmp.addEdgeReader(new ExtendsEdgeReader());
		tmp.addEdgeReader(new ImplementsEdgeReader());
		tmp.addEdgeReader(new AssociationEdgeReader());
		tmp.addEdgeReader(new DependencyEdgeReader());
		tmp.addEdgeReader(new AssociationBidirectionalEdgeReader());
		tmp.addEdgeReader(new DependencyBidirectionalEdgeReader());
		tmp.addEdgeReader(new GraphVizEdgeReader());

		tmp.addNodeReader(new NormalNodeReader());
		tmp.addNodeReader(new GraphVizNodeReader());

		tmp.addEdgeChecker(new AssociationDependencyChecker());
		tmp.addEdgeChecker(new BidirectionalEdgeChecker());
		tmp.addEdgeChecker(new DuplicateDependencyEdgeChecker());

		tmp.process();
	}
}
