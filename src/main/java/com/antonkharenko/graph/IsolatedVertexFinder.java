package com.antonkharenko.graph;

import com.google.common.collect.Lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

public class IsolatedVertexFinder<V> implements IIsolatedVertexFinder<V> {

  private static Logger LOGGER = LoggerFactory.getLogger(IsolatedVertexFinder.class);

  public List<V> findIsolatedVertex(Collection<V> allVertex, IShortestPath<V> path, V source) {
    List<V> isolatedVertexes = Lists.newArrayList();
    for (V v : allVertex) {
      if (!v.equals(source) && path.pathTo(v) == null) {
        LOGGER.debug("Isolated vertex {}, because path {}", v, path);
        isolatedVertexes.add(v);
      }
    }
    return isolatedVertexes;
  }
}
