package com.algorithmics.np.vc.preprocessing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.algorithmics.ds.graphs.UndirectedGraph;
import com.algorithmics.np.preprocessor.SpecificFormatReader;

public class TGFGraphReader implements SpecificFormatReader<UndirectedGraph> {

    @Override
    public UndirectedGraph readFromFile(String filePath) {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            List<String> lines = stream.collect(Collectors.toList());
            UndirectedGraph graph = new UndirectedGraph();
            boolean processingEdge = false;
            for (String line : lines) {
                if (line.startsWith("#")) {
                    processingEdge = true;
                    continue;
                }
                if (!processingEdge) {

                    String[] lineArray = line.split(" ", 2);
                    int vertexId = Integer.valueOf(lineArray[0]);
                    graph.addVertex(vertexId);
                    // String vertexLabel=lineArray[1];
                }
                if (processingEdge) {
                    String[] lineArray = line.split(" ", 3);
                    int vertexId1 = Integer.valueOf(lineArray[0]);
                    int vertexId2 = Integer.valueOf(lineArray[1]);
                    // String vertexLabel=lineArray[2];
                    graph.addEdge(vertexId1, vertexId2);
                }
            }
            return graph;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getExtension() {
        return "tgf";
    }
}
