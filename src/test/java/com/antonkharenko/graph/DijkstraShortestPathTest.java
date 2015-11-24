package com.antonkharenko.graph;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Anton Kharenko
 */
public class DijkstraShortestPathTest {

  private Graph<String> undirectedGraph;

  @Before
  public void init() {
    // Init test graph
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
  }

  @Test
  public void testGetSourceVertex() {
    // Given
    String sourceVertex = "1";
    DijkstraShortestPath<String> sp = new DijkstraShortestPath<>(undirectedGraph, sourceVertex);

    // When
    String actualSourceVertex = sp.getSourceVertex();

    // Then
    Assert.assertEquals(sourceVertex, actualSourceVertex);
  }

  @Test
  public void testPathTo() {
    // Given
    DijkstraShortestPath<String> sp = new DijkstraShortestPath<>(undirectedGraph, "1");

    // When
    List<String> pathTo1 = sp.pathTo("1");
    List<String> pathTo2 = sp.pathTo("2");
    List<String> pathTo3 = sp.pathTo("3");
    List<String> pathTo4 = sp.pathTo("4");
    List<String> pathTo5 = sp.pathTo("5");
    List<String> pathTo6 = sp.pathTo("6");
    List<String> pathTo7 = sp.pathTo("7");
    List<String> pathTo8 = sp.pathTo("8");

    // Then
    Assert.assertTrue(Arrays.equals(pathTo1.toArray(), new String[] {"1"}));
    Assert.assertTrue(Arrays.equals(pathTo2.toArray(), new String[] {"1", "2"}));
    Assert.assertTrue(Arrays.equals(pathTo3.toArray(), new String[] {"1", "3"}));
    Assert.assertTrue(Arrays.equals(pathTo4.toArray(), new String[] {"1", "2", "4"}));
    Assert.assertTrue(Arrays.equals(pathTo5.toArray(), new String[] {"1", "2", "4", "5"}));
    Assert.assertTrue(Arrays.equals(pathTo6.toArray(), new String[] {"1", "3", "6"}));
    Assert.assertNull(pathTo7);
    Assert.assertNull(pathTo8);
  }

  @Test
  public void testNextVertexTo() {
    // Given
    DijkstraShortestPath<String> sp = new DijkstraShortestPath<>(undirectedGraph, "1");

    // When
    String nextVertexTo1 = sp.nextVertexTo("1");
    String nextVertexTo2 = sp.nextVertexTo("2");
    String nextVertexTo3 = sp.nextVertexTo("3");
    String nextVertexTo4 = sp.nextVertexTo("4");
    String nextVertexTo5 = sp.nextVertexTo("5");
    String nextVertexTo6 = sp.nextVertexTo("6");
    String nextVertexTo7 = sp.nextVertexTo("7");
    String nextVertexTo8 = sp.nextVertexTo("8");

    // Then
    Assert.assertEquals(nextVertexTo1, "1");
    Assert.assertEquals(nextVertexTo2, "2");
    Assert.assertEquals(nextVertexTo3, "3");
    Assert.assertEquals(nextVertexTo4, "2");
    Assert.assertEquals(nextVertexTo5, "2");
    Assert.assertEquals(nextVertexTo6, "3");
    Assert.assertNull(nextVertexTo7);
    Assert.assertNull(nextVertexTo8);
  }

  @Test
  public void testPathToTwoVertixes() {
    // Given
    // Init test graph
    Graph<String> graph = new Graph<>();
    for (String vertex : Arrays.asList("1", "2")) {
      graph.addVertex(vertex);
    }
    graph.addEdge(new Edge<>("1", "2", 1.0));
    graph.addEdge(new Edge<>("2", "1", 1.0));
    DijkstraShortestPath<String> sp = new DijkstraShortestPath<>(graph, "1");

    // When
    List<String> pathTo1 = sp.pathTo("1");
    List<String> pathTo2 = sp.pathTo("2");

    // Then
    Assert.assertTrue(Arrays.equals(pathTo1.toArray(), new String[] {"1"}));
    Assert.assertTrue(Arrays.equals(pathTo2.toArray(), new String[] {"1", "2"}));

  }

  @Test
  public void testNextVertexToTwoVertixes() {
    // Given
    // Init test graph
    Graph<String> graph = new Graph<>();
    for (String vertex : Arrays.asList("1", "2")) {
      graph.addVertex(vertex);
    }
    graph.addEdge(new Edge<>("1", "2", 1.0));
    graph.addEdge(new Edge<>("2", "1", 1.0));
    DijkstraShortestPath<String> sp = new DijkstraShortestPath<>(graph, "1");

    // When
    String nextVertexTo1 = sp.nextVertexTo("1");
    String nextVertexTo2 = sp.nextVertexTo("2");

    // Then
    Assert.assertEquals(nextVertexTo1, "1");
    Assert.assertEquals(nextVertexTo2, "2");
  }

}
