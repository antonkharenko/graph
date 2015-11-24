package com.antonkharenko.graph;

import java.util.Objects;

/**
 * @author Anton Kharenko
 */
public class Edge<V> implements IEdge<V> {

  private final V from;
  private final V to;
  private final double weight;

  public Edge(V from, V to, double weight) {
    this.from = from;
    this.to = to;
    this.weight = weight;
  }

  public V getFrom() {
    return from;
  }

  public V getTo() {
    return to;
  }

  public double getWeight() {
    return weight;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Edge<?> edge = (Edge<?>) o;
    return Objects.equals(weight, edge.weight) &&
        Objects.equals(from, edge.from) &&
        Objects.equals(to, edge.to);
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, to, weight);
  }

  @Override
  public String toString() {
    return "Edge [from=" + from + ",to=" + to + ",weight=" + weight + ']';
  }
}
