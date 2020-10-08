package com.killrvideo.dse.graph;

import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.process.computer.Computer;
import org.apache.tinkerpop.gremlin.process.computer.GraphComputer;
import org.apache.tinkerpop.gremlin.process.remote.RemoteConnection;
import org.apache.tinkerpop.gremlin.process.traversal.Traversal;
import org.apache.tinkerpop.gremlin.process.traversal.TraversalStrategies;
import org.apache.tinkerpop.gremlin.process.traversal.TraversalStrategy;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.step.map.AddEdgeStartStep;
import org.apache.tinkerpop.gremlin.process.traversal.step.map.AddVertexStartStep;
import org.apache.tinkerpop.gremlin.process.traversal.step.map.GraphStep;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;

public class KillrVideoTraversalSource extends KillrVideoTraversalSourceDsl {
  public KillrVideoTraversalSource(Graph graph) {
    super(graph);
  }

  public KillrVideoTraversalSource(Graph graph, TraversalStrategies strategies) {
    super(graph, strategies);
  }

  @Override
  public KillrVideoTraversalSource clone() {
    return (KillrVideoTraversalSource) super.clone();
  }

  @Override
  public KillrVideoTraversalSource with(String key) {
    return (KillrVideoTraversalSource) super.with(key);
  }

  @Override
  public KillrVideoTraversalSource with(String key, Object value) {
    return (KillrVideoTraversalSource) super.with(key,value);
  }

  @Override
  public KillrVideoTraversalSource withStrategies(TraversalStrategy... traversalStrategies) {
    return (KillrVideoTraversalSource) super.withStrategies(traversalStrategies);
  }

  @Override
  public KillrVideoTraversalSource withoutStrategies(
      Class<? extends TraversalStrategy>... traversalStrategyClasses) {
    return (KillrVideoTraversalSource) super.withoutStrategies(traversalStrategyClasses);
  }

  @Override
  public KillrVideoTraversalSource withComputer(Computer computer) {
    return (KillrVideoTraversalSource) super.withComputer(computer);
  }

  @Override
  public KillrVideoTraversalSource withComputer(Class<? extends GraphComputer> graphComputerClass) {
    return (KillrVideoTraversalSource) super.withComputer(graphComputerClass);
  }

  @Override
  public KillrVideoTraversalSource withComputer() {
    return (KillrVideoTraversalSource) super.withComputer();
  }

  @Override
  public <A> KillrVideoTraversalSource withSideEffect(String key, Supplier<A> initialValue,
      BinaryOperator<A> reducer) {
    return (KillrVideoTraversalSource) super.withSideEffect(key,initialValue,reducer);
  }

  @Override
  public <A> KillrVideoTraversalSource withSideEffect(String key, A initialValue,
      BinaryOperator<A> reducer) {
    return (KillrVideoTraversalSource) super.withSideEffect(key,initialValue,reducer);
  }

  @Override
  public <A> KillrVideoTraversalSource withSideEffect(String key, A initialValue) {
    return (KillrVideoTraversalSource) super.withSideEffect(key,initialValue);
  }

  @Override
  public <A> KillrVideoTraversalSource withSideEffect(String key, Supplier<A> initialValue) {
    return (KillrVideoTraversalSource) super.withSideEffect(key,initialValue);
  }

  @Override
  public <A> KillrVideoTraversalSource withSack(Supplier<A> initialValue,
      UnaryOperator<A> splitOperator, BinaryOperator<A> mergeOperator) {
    return (KillrVideoTraversalSource) super.withSack(initialValue,splitOperator,mergeOperator);
  }

  @Override
  public <A> KillrVideoTraversalSource withSack(A initialValue, UnaryOperator<A> splitOperator,
      BinaryOperator<A> mergeOperator) {
    return (KillrVideoTraversalSource) super.withSack(initialValue,splitOperator,mergeOperator);
  }

  @Override
  public <A> KillrVideoTraversalSource withSack(A initialValue) {
    return (KillrVideoTraversalSource) super.withSack(initialValue);
  }

  @Override
  public <A> KillrVideoTraversalSource withSack(Supplier<A> initialValue) {
    return (KillrVideoTraversalSource) super.withSack(initialValue);
  }

  @Override
  public <A> KillrVideoTraversalSource withSack(Supplier<A> initialValue,
      UnaryOperator<A> splitOperator) {
    return (KillrVideoTraversalSource) super.withSack(initialValue,splitOperator);
  }

  @Override
  public <A> KillrVideoTraversalSource withSack(A initialValue, UnaryOperator<A> splitOperator) {
    return (KillrVideoTraversalSource) super.withSack(initialValue,splitOperator);
  }

  @Override
  public <A> KillrVideoTraversalSource withSack(Supplier<A> initialValue,
      BinaryOperator<A> mergeOperator) {
    return (KillrVideoTraversalSource) super.withSack(initialValue,mergeOperator);
  }

  @Override
  public <A> KillrVideoTraversalSource withSack(A initialValue, BinaryOperator<A> mergeOperator) {
    return (KillrVideoTraversalSource) super.withSack(initialValue,mergeOperator);
  }

  @Override
  public KillrVideoTraversalSource withBulk(boolean useBulk) {
    return (KillrVideoTraversalSource) super.withBulk(useBulk);
  }

  @Override
  public KillrVideoTraversalSource withPath() {
    return (KillrVideoTraversalSource) super.withPath();
  }

  @Override
  public KillrVideoTraversalSource withRemote(Configuration conf) {
    return (KillrVideoTraversalSource) super.withRemote(conf);
  }

  @Override
  public KillrVideoTraversalSource withRemote(String configFile) throws Exception {
    return (KillrVideoTraversalSource) super.withRemote(configFile);
  }

  @Override
  public KillrVideoTraversalSource withRemote(RemoteConnection connection) {
    return (KillrVideoTraversalSource) super.withRemote(connection);
  }

  @Override
  public KillrVideoTraversal<Vertex, Vertex> videos(String... videoIds) {
    KillrVideoTraversalSource clone = this.clone();
    return new DefaultKillrVideoTraversal (clone, super.videos(videoIds).asAdmin());
  }

  @Override
  public KillrVideoTraversal<Vertex, Vertex> users(String... userIds) {
    KillrVideoTraversalSource clone = this.clone();
    return new DefaultKillrVideoTraversal (clone, super.users(userIds).asAdmin());
  }

  @Override
  public KillrVideoTraversal<Vertex, Vertex> tags(String... tags) {
    KillrVideoTraversalSource clone = this.clone();
    return new DefaultKillrVideoTraversal (clone, super.tags(tags).asAdmin());
  }

  @Override
  public KillrVideoTraversal<Vertex, Vertex> filteredTransversal(String vertexName, String vertexId,
      String... ids) {
    KillrVideoTraversalSource clone = this.clone();
    return new DefaultKillrVideoTraversal (clone, super.filteredTransversal(vertexName,vertexId,ids).asAdmin());
  }

  @Override
  public KillrVideoTraversal<Vertex, Vertex> video(UUID videoId, String name, Date added_date,
      String description, String previewImageLocation) {
    KillrVideoTraversalSource clone = this.clone();
    return new DefaultKillrVideoTraversal (clone, super.video(videoId,name,added_date,description,previewImageLocation).asAdmin());
  }

  @Override
  public KillrVideoTraversal<Vertex, Vertex> user(UUID userId, String email, Date added_date) {
    KillrVideoTraversalSource clone = this.clone();
    return new DefaultKillrVideoTraversal (clone, super.user(userId,email,added_date).asAdmin());
  }

  @Override
  public KillrVideoTraversal<Vertex, Vertex> tag(String name, Date tagged_date) {
    KillrVideoTraversalSource clone = this.clone();
    return new DefaultKillrVideoTraversal (clone, super.tag(name,tagged_date).asAdmin());
  }

  @Override
  public KillrVideoTraversal<Vertex, Vertex> addV() {
    KillrVideoTraversalSource clone = this.clone();
    clone.getBytecode().addStep(GraphTraversal.Symbols.addV);
    DefaultKillrVideoTraversal traversal = new DefaultKillrVideoTraversal(clone);
    return (KillrVideoTraversal) traversal.asAdmin().addStep(new AddVertexStartStep(traversal, (String) null));
  }

  @Override
  public KillrVideoTraversal<Vertex, Vertex> addV(String label) {
    KillrVideoTraversalSource clone = this.clone();
    clone.getBytecode().addStep(GraphTraversal.Symbols.addV, label);
    DefaultKillrVideoTraversal traversal = new DefaultKillrVideoTraversal(clone);
    return (KillrVideoTraversal) traversal.asAdmin().addStep(new AddVertexStartStep(traversal, label));
  }

  @Override
  public KillrVideoTraversal<Vertex, Vertex> addV(Traversal vertexLabelTraversal) {
    KillrVideoTraversalSource clone = this.clone();
    clone.getBytecode().addStep(GraphTraversal.Symbols.addV, vertexLabelTraversal);
    DefaultKillrVideoTraversal traversal = new DefaultKillrVideoTraversal(clone);
    return (KillrVideoTraversal) traversal.asAdmin().addStep(new AddVertexStartStep(traversal, vertexLabelTraversal));
  }

  @Override
  public KillrVideoTraversal<Edge, Edge> addE(String label) {
    KillrVideoTraversalSource clone = this.clone();
    clone.getBytecode().addStep(GraphTraversal.Symbols.addV, label);
    DefaultKillrVideoTraversal traversal = new DefaultKillrVideoTraversal(clone);
    return (KillrVideoTraversal) traversal.asAdmin().addStep(new AddEdgeStartStep(traversal, label));
  }

  @Override
  public KillrVideoTraversal<Edge, Edge> addE(Traversal edgeLabelTraversal) {
    KillrVideoTraversalSource clone = this.clone();
    clone.getBytecode().addStep(GraphTraversal.Symbols.addV, edgeLabelTraversal);
    DefaultKillrVideoTraversal traversal = new DefaultKillrVideoTraversal(clone);
    return (KillrVideoTraversal) traversal.asAdmin().addStep(new AddEdgeStartStep(traversal, edgeLabelTraversal));
  }

  @Override
  public KillrVideoTraversal<Vertex, Vertex> V(Object... vertexIds) {
    KillrVideoTraversalSource clone = this.clone();
    clone.getBytecode().addStep(GraphTraversal.Symbols.V, vertexIds);
    DefaultKillrVideoTraversal traversal = new DefaultKillrVideoTraversal(clone);
    return (KillrVideoTraversal) traversal.asAdmin().addStep(new GraphStep(traversal, Vertex.class, true, vertexIds));
  }

  @Override
  public KillrVideoTraversal<Edge, Edge> E(Object... edgeIds) {
    KillrVideoTraversalSource clone = this.clone();
    clone.getBytecode().addStep(GraphTraversal.Symbols.E, edgeIds);
    DefaultKillrVideoTraversal traversal = new DefaultKillrVideoTraversal(clone);
    return (KillrVideoTraversal) traversal.asAdmin().addStep(new GraphStep(traversal, Edge.class, true, edgeIds));
  }

  @Override
  public Optional<Class> getAnonymousTraversalClass() {
    return Optional.of(__.class);
  }
}
