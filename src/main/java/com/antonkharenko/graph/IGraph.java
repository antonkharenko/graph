package com.antonkharenko.graph;

import java.util.Collection;

/**
 * @author Anton Kharenko
 */
public interface IGraph<V> {

  Collection<V> getVertices();

  Collection<IEdge<V>> getEdges(V fromVertex);

  boolean hasVertex(V vertex);
}
