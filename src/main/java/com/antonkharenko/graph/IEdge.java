package com.antonkharenko.graph;

/**
 * @author Anton Kharenko
 */
public interface IEdge<V> {

  V getFrom();

  V getTo();

  double getWeight();
}
