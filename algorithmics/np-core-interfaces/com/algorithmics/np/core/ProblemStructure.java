package com.algorithmics.np.core;

/**
 * This represents any data structure to be used as a Problem<br>
 * e.g. Graph, Formula, Set, etc.
 * 
 * This also provides an additional abstraction between raw data-structure<br>
 * and the algorithmic problems because:<br>
 * 
 * - a certain class of problems may have similar characteristics<br>
 * (e.g. having parameter k for Optimization Problem)<br>
 * 
 * - a certain data structure can be used for different problems<br>
 * e.g. a Graph can be used for VertexCover, Graph Coloring, etc.
 *
 */
public interface ProblemStructure {

}
