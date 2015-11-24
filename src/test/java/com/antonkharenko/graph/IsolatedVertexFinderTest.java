package com.antonkharenko.graph;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class IsolatedVertexFinderTest {
  private Graph<String> undirectedGraph;
  private IIsolatedVertexFinder<String> isolatedVertexFinder;
  private String sourceVertex;
  private DijkstraShortestPath<String> sp;

  @Before
  public void init() {
    // Init test graph
    isolatedVertexFinder = new IsolatedVertexFinder<>();
    undirectedGraph = new Graph<>();
    for (String vertex : Arrays.asList("1", "2", "3", "4", "5", "6", "7")) {
      undirectedGraph.addVertex(vertex);
    }
    undirectedGraph.addEdge(new Edge<>("1", "2", 1.0));
    undirectedGraph.addEdge(new Edge<>("2", "1", 1.0));

    undirectedGraph.addEdge(new Edge<>("1", "3", 1.0));
    undirectedGraph.addEdge(new Edge<>("3", "1", 1.0));

    undirectedGraph.addEdge(new Edge<>("2", "4", 1.0));
    undirectedGraph.addEdge(new Edge<>("4", "2", 1.0));

    undirectedGraph.addEdge(new Edge<>("3", "4", 1.0));
    undirectedGraph.addEdge(new Edge<>("4", "3", 1.0));

    undirectedGraph.addEdge(new Edge<>("3", "6", 1.0));
    undirectedGraph.addEdge(new Edge<>("6", "3", 1.0));

    undirectedGraph.addEdge(new Edge<>("4", "5", 1.0));
    undirectedGraph.addEdge(new Edge<>("5", "4", 1.0));

    undirectedGraph.addEdge(new Edge<>("4", "6", 1.0));
    undirectedGraph.addEdge(new Edge<>("6", "4", 1.0));
    sourceVertex = "1";
    sp = new DijkstraShortestPath<>(undirectedGraph, sourceVertex);
  }

  @Test
  public void testFindIsolatedVertex() throws Exception {
    List<String> isolatedVertex =
        isolatedVertexFinder.findIsolatedVertex(undirectedGraph.getVertices(), sp, sourceVertex);
    Assert.assertNotNull(isolatedVertex);
    Assert.assertEquals(1, isolatedVertex.size());
    Assert.assertEquals("7", isolatedVertex.get(0));
  }
}
