package com.antonkharenko.graph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraShortestPath<V> implements IShortestPath<V> {

  private static final Logger LOGGER = LoggerFactory.getLogger(DijkstraShortestPath.class);

  private HashMap<V, IEdge<V>> edgeTo;
  private HashMap<V, Double> distTo;
  private PriorityQueue<VertexPriority<V>> priorityQueue;
  private V sourceVertex;

  public DijkstraShortestPath(IGraph<V> graph, V sourceVertex) {
    this.sourceVertex = sourceVertex;
    Collection<V> vertices = graph.getVertices();
    int vertexNum = vertices.size();
    edgeTo = new HashMap<>(vertexNum);
    distTo = new HashMap<>(vertexNum);

    priorityQueue = new PriorityQueue<>(vertexNum, new VertexPriorityComparator());

    for (V vertex : vertices) {
      distTo.put(vertex, Double.POSITIVE_INFINITY);
    }
    distTo.put(sourceVertex, 0.0);
    priorityQueue.add(new VertexPriority<>(sourceVertex));
    while (!priorityQueue.isEmpty()) {
      V currentVertex = priorityQueue.remove().getVertex();
      for (IEdge<V> edge : graph.getEdges(currentVertex)) {
        relaxEdge(edge);
      }
    }
    LOGGER.debug("Edge connections: {}", edgeTo);
  }

  private void relaxEdge(IEdge<V> edge) {
    double oldDistTo = distTo.get(edge.getTo());
    double newDistTo = distTo.get(edge.getFrom()) + edge.getWeight();
    if (Double.compare(oldDistTo, newDistTo) > 0) {
      distTo.put(edge.getTo(), newDistTo);
      edgeTo.put(edge.getTo(), edge);
      priorityQueue.remove(new VertexPriority<>(edge.getTo()));
      priorityQueue.add(new VertexPriority<>(edge.getTo(), newDistTo));
    }
  }

  @Override
  public V getSourceVertex() {
    return sourceVertex;
  }

  @Override
  public List<V> pathTo(V destinationVertex) {
    // Returns path from source vertex to destination vertex
    LinkedList<V> path = new LinkedList<>();
    V nextVertex = destinationVertex;
    LOGGER.debug("Source : {}, destination: {}", sourceVertex, destinationVertex);
    while (!sourceVertex.equals(nextVertex)) {
      path.addFirst(nextVertex);
      LOGGER.debug("Add {} to the path", nextVertex);
      if (edgeTo.containsKey(destinationVertex)) {
        LOGGER.debug("Edge contains {} to the path", nextVertex);
        IEdge<V> nextEdge = edgeTo.get(nextVertex);
        nextVertex = nextEdge.getFrom();
      } else {
        // Returns null in case if there is no path to destination vertex
        return null;
      }
    }
    path.addFirst(nextVertex);
    return path;
  }

  @Override
  public V nextVertexTo(V destinationVertex) {
    if (sourceVertex.equals(destinationVertex)) {
      return destinationVertex;
    } else if (!edgeTo.containsKey(destinationVertex)) {
      // Returns null in case if there is no path to destination vertex
      return null;
    } else {
      V nextVertex = destinationVertex;
      V prevVertex = destinationVertex;
      while (!sourceVertex.equals(prevVertex)) {
        IEdge<V> nextEdge = edgeTo.get(prevVertex);
        nextVertex = nextEdge.getTo();
        prevVertex = nextEdge.getFrom();
      }
      return nextVertex;
    }
  }

  private static final class VertexPriority<V> {
    private final V vertex;
    private final double priority;

    private VertexPriority(V vertex) {
      this(vertex, 0.0);
    }

    private VertexPriority(V vertex, double priority) {
      this.vertex = vertex;
      this.priority = priority;
    }

    public V getVertex() {
      return vertex;
    }

    public double getPriority() {
      return priority;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;

      VertexPriority that = (VertexPriority) o;

      return !(vertex != null ? !vertex.equals(that.vertex) : that.vertex != null);
    }

    @Override
    public int hashCode() {
      return vertex != null ? vertex.hashCode() : 0;
    }
  }

  @Override
  public String toString() {
    return "DijkstraShortestPath{" +
        "edgeTo=" + edgeTo +
        '}';
  }

  private static class VertexPriorityComparator implements Comparator<VertexPriority> {
    @Override
    public int compare(VertexPriority o1, VertexPriority o2) {
      return Double.compare(o1.getPriority(), o2.getPriority());
    }
  }
}
