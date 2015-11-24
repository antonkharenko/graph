package com.antonkharenko.graph;

import java.util.List;

/**
 * @author Anton Kharenko
 */
public interface IShortestPath<V> {

  V getSourceVertex();

  List<V> pathTo(V destinationVertex);

  V nextVertexTo(V destinationVertex);

}
