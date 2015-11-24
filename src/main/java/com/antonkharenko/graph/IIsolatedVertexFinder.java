package com.antonkharenko.graph;

import java.util.Collection;
import java.util.List;

public interface IIsolatedVertexFinder<V> {

  List<V> findIsolatedVertex(Collection<V> allVertex, IShortestPath<V> path, V source);
}
