package com.antonkharenko.graph;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * @author Anton Kharenko
 */
public class Graph<V> implements IGraph<V> {

  private final Collection<V> vertices = new HashSet<>();
  private final Multimap<V, IEdge<V>> edges = LinkedHashMultimap.create();

  public Graph() {}

  public void addVertex(V v) {
    vertices.add(v);
  }

  public void removeVertex(V v) {
    vertices.remove(v);
  }

  @Override
  public boolean hasVertex(V v) {
    return vertices.contains(v);
  }

  public void addEdge(IEdge<V> e) {
    edges.put(e.getFrom(), e);
  }

  public void removeEdge(IEdge<V> e) {
    edges.remove(e.getFrom(), e);
  }

  @Override
  public Collection<V> getVertices() {
    return Collections.unmodifiableCollection(vertices);
  }

  @Override
  public Collection<IEdge<V>> getEdges(V fromVertex) {
    return Collections.unmodifiableCollection(edges.get(fromVertex));
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Graph{");
    sb.append("vertices=");
    sb.append(vertices);
    sb.append(" ");
    for (V v : edges.keySet()) {
      for (IEdge<V> viEdge : edges.get(v)) {
        sb.append("Edge : ");
        sb.append(viEdge.getFrom());
        sb.append(" -> ");
        sb.append(viEdge.getTo());
        sb.append(" ");
      }
    }
    return sb.toString();
  }
}
