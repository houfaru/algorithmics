package com.algorithmics.np.vc.preprocessing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.algorithmics.np.preprocessor.SpecificFormatReader;
import com.algorithmics.np.vc.instance.PlainGraph;

public class TGFGraphReader implements SpecificFormatReader<PlainGraph>{

	@Override
	public PlainGraph readFromFile(String filePath) {
		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
			List<String> lines = stream.collect(Collectors.toList());
			PlainGraph graph=new PlainGraph();
			boolean processingEdge=false;
			for(String line:lines) {
				if(line.startsWith("#")) {
					processingEdge=true;
					continue;
				}
				if(!processingEdge) {
					
					String[] lineArray = line.split(" ", 2);
					int vertexId=Integer.valueOf(lineArray[0]);
					graph.addVertex(vertexId);
					//String vertexLabel=lineArray[1];
				}
				if(processingEdge) {
					String[] lineArray = line.split(" ", 3);
					int vertexId1=Integer.valueOf(lineArray[0]);
					int vertexId2=Integer.valueOf(lineArray[1]);
					//String vertexLabel=lineArray[2];
					graph.addEdge(vertexId1, vertexId2);
				}
			}
			return graph;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
