package com.killrvideo.dse.graph;

import java.lang.Comparable;
import java.lang.Double;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Number;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.tinkerpop.gremlin.process.computer.VertexProgram;
import org.apache.tinkerpop.gremlin.process.traversal.Order;
import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.process.traversal.Pop;
import org.apache.tinkerpop.gremlin.process.traversal.Scope;
import org.apache.tinkerpop.gremlin.process.traversal.Traversal;
import org.apache.tinkerpop.gremlin.process.traversal.Traverser;
import org.apache.tinkerpop.gremlin.process.traversal.step.util.Tree;
import org.apache.tinkerpop.gremlin.process.traversal.traverser.util.TraverserSet;
import org.apache.tinkerpop.gremlin.process.traversal.util.TraversalMetrics;
import org.apache.tinkerpop.gremlin.structure.Column;
import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Property;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;

public interface KillrVideoTraversal<S, E> extends KillrVideoTraversalDsl<S, E> {
  @Override
  default KillrVideoTraversal<S, Vertex> rated() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.rated();
  }

  @Override
  default KillrVideoTraversal<S, Vertex> rated(int min, int max) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.rated(min,max);
  }

  @Override
  default KillrVideoTraversal<S, Vertex> rated(UUID userId, Integer rating) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.rated(userId,rating);
  }

  @Override
  default KillrVideoTraversal<S, Vertex> watched() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.watched();
  }

  @Override
  default KillrVideoTraversal<S, Vertex> uploaders() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.uploaders();
  }

  @Override
  default KillrVideoTraversal<S, Vertex> user(UUID userId) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.user(userId);
  }

  @Override
  default KillrVideoTraversal<S, Vertex> uploaded(UUID userId) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.uploaded(userId);
  }

  @Override
  default KillrVideoTraversal<S, Vertex> taggers() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.taggers();
  }

  @Override
  default KillrVideoTraversal<S, Vertex> tag(String name, Date tagged_date) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.tag(name,tagged_date);
  }

  @Override
  default KillrVideoTraversal<S, Vertex> taggedWith(String name, Date tagged_date) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.taggedWith(name,tagged_date);
  }

  @Override
  default KillrVideoTraversal<S, ?> add(Traversal<?, ?> mutationTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.add(mutationTraversal);
  }

  @Override
  default KillrVideoTraversal<S, Map<String, Object>> recommendByUserRating(int recommendations,
      int minRating, int numRatingsToSample, int localUserRatingsToSample) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.recommendByUserRating(recommendations,minRating,numRatingsToSample,localUserRatingsToSample);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> map(Function<Traverser<E>, E2> function) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.map(function);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> map(Traversal<?, E2> mapTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.map(mapTraversal);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> flatMap(Function<Traverser<E>, Iterator<E2>> function) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.flatMap(function);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> flatMap(Traversal<?, E2> flatMapTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.flatMap(flatMapTraversal);
  }

  @Override
  default KillrVideoTraversal<S, Object> id() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.id();
  }

  @Override
  default KillrVideoTraversal<S, String> label() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.label();
  }

  @Override
  default KillrVideoTraversal<S, E> identity() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.identity();
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> constant(E2 e) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.constant(e);
  }

  @Override
  default KillrVideoTraversal<S, Vertex> V(Object... vertexIdsOrElements) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.V(vertexIdsOrElements);
  }

  @Override
  default KillrVideoTraversal<S, Vertex> to(Direction direction, String... edgeLabels) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.to(direction,edgeLabels);
  }

  @Override
  default KillrVideoTraversal<S, Vertex> out(String... edgeLabels) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.out(edgeLabels);
  }

  @Override
  default KillrVideoTraversal<S, Vertex> in(String... edgeLabels) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.in(edgeLabels);
  }

  @Override
  default KillrVideoTraversal<S, Vertex> both(String... edgeLabels) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.both(edgeLabels);
  }

  @Override
  default KillrVideoTraversal<S, Edge> toE(Direction direction, String... edgeLabels) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.toE(direction,edgeLabels);
  }

  @Override
  default KillrVideoTraversal<S, Edge> outE(String... edgeLabels) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.outE(edgeLabels);
  }

  @Override
  default KillrVideoTraversal<S, Edge> inE(String... edgeLabels) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.inE(edgeLabels);
  }

  @Override
  default KillrVideoTraversal<S, Edge> bothE(String... edgeLabels) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.bothE(edgeLabels);
  }

  @Override
  default KillrVideoTraversal<S, Vertex> toV(Direction direction) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.toV(direction);
  }

  @Override
  default KillrVideoTraversal<S, Vertex> inV() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.inV();
  }

  @Override
  default KillrVideoTraversal<S, Vertex> outV() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.outV();
  }

  @Override
  default KillrVideoTraversal<S, Vertex> bothV() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.bothV();
  }

  @Override
  default KillrVideoTraversal<S, Vertex> otherV() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.otherV();
  }

  @Override
  default KillrVideoTraversal<S, E> order() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.order();
  }

  @Override
  default KillrVideoTraversal<S, E> order(Scope scope) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.order(scope);
  }

  @Override
  default <E2> KillrVideoTraversal<S, ? extends Property<E2>> properties(String... propertyKeys) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.properties(propertyKeys);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> values(String... propertyKeys) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.values(propertyKeys);
  }

  @Override
  default <E2> KillrVideoTraversal<S, Map<String, E2>> propertyMap(String... propertyKeys) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.propertyMap(propertyKeys);
  }

  @Override
  default <E2> KillrVideoTraversal<S, Map<Object, E2>> valueMap(String... propertyKeys) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.valueMap(propertyKeys);
  }

  @Override
  default <E2> KillrVideoTraversal<S, Map<Object, E2>> valueMap(boolean includeTokens,
      String... propertyKeys) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.valueMap(includeTokens,propertyKeys);
  }

  @Override
  default KillrVideoTraversal<S, String> key() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.key();
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> value() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.value();
  }

  @Override
  default KillrVideoTraversal<S, Path> path() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.path();
  }

  @Override
  default <E2> KillrVideoTraversal<S, Map<String, E2>> match(Traversal<?, ?>... matchTraversals) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.match(matchTraversals);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> sack() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.sack();
  }

  @Override
  default KillrVideoTraversal<S, Integer> loops() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.loops();
  }

  @Override
  default KillrVideoTraversal<S, Integer> loops(String loopName) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.loops(loopName);
  }

  @Override
  default <E2> KillrVideoTraversal<S, Map<String, E2>> project(String projectKey,
      String... otherProjectKeys) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.project(projectKey,otherProjectKeys);
  }

  @Override
  default <E2> KillrVideoTraversal<S, Map<String, E2>> select(Pop pop, String selectKey1,
      String selectKey2, String... otherSelectKeys) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.select(pop,selectKey1,selectKey2,otherSelectKeys);
  }

  @Override
  default <E2> KillrVideoTraversal<S, Map<String, E2>> select(String selectKey1, String selectKey2,
      String... otherSelectKeys) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.select(selectKey1,selectKey2,otherSelectKeys);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> select(Pop pop, String selectKey) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.select(pop,selectKey);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> select(String selectKey) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.select(selectKey);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> select(Pop pop, Traversal<S, E2> keyTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.select(pop,keyTraversal);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> select(Traversal<S, E2> keyTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.select(keyTraversal);
  }

  @Override
  default <E2> KillrVideoTraversal<S, Collection<E2>> select(Column column) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.select(column);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> unfold() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.unfold();
  }

  @Override
  default KillrVideoTraversal<S, List<E>> fold() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.fold();
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> fold(E2 seed, BiFunction<E2, E, E2> foldFunction) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.fold(seed,foldFunction);
  }

  @Override
  default KillrVideoTraversal<S, Long> count() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.count();
  }

  @Override
  default KillrVideoTraversal<S, Long> count(Scope scope) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.count(scope);
  }

  @Override
  default <E2 extends Number> KillrVideoTraversal<S, E2> sum() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.sum();
  }

  @Override
  default <E2 extends Number> KillrVideoTraversal<S, E2> sum(Scope scope) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.sum(scope);
  }

  @Override
  default <E2 extends Comparable> KillrVideoTraversal<S, E2> max() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.max();
  }

  @Override
  default <E2 extends Comparable> KillrVideoTraversal<S, E2> max(Scope scope) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.max(scope);
  }

  @Override
  default <E2 extends Comparable> KillrVideoTraversal<S, E2> min() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.min();
  }

  @Override
  default <E2 extends Comparable> KillrVideoTraversal<S, E2> min(Scope scope) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.min(scope);
  }

  @Override
  default <E2 extends Number> KillrVideoTraversal<S, E2> mean() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.mean();
  }

  @Override
  default <E2 extends Number> KillrVideoTraversal<S, E2> mean(Scope scope) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.mean(scope);
  }

  @Override
  default <K, V> KillrVideoTraversal<S, Map<K, V>> group() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.group();
  }

  @Override
  default <K> KillrVideoTraversal<S, Map<K, Long>> groupCount() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.groupCount();
  }

  @Override
  default KillrVideoTraversal<S, Tree> tree() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.tree();
  }

  @Override
  default KillrVideoTraversal<S, Vertex> addV(String vertexLabel) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.addV(vertexLabel);
  }

  @Override
  default KillrVideoTraversal<S, Vertex> addV(Traversal<?, String> vertexLabelTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.addV(vertexLabelTraversal);
  }

  @Override
  default KillrVideoTraversal<S, Vertex> addV() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.addV();
  }

  @Override
  default KillrVideoTraversal<S, Edge> addE(String edgeLabel) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.addE(edgeLabel);
  }

  @Override
  default KillrVideoTraversal<S, Edge> addE(Traversal<?, String> edgeLabelTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.addE(edgeLabelTraversal);
  }

  @Override
  default KillrVideoTraversal<S, E> to(String toStepLabel) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.to(toStepLabel);
  }

  @Override
  default KillrVideoTraversal<S, E> from(String fromStepLabel) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.from(fromStepLabel);
  }

  @Override
  default KillrVideoTraversal<S, E> to(Traversal<?, Vertex> toVertex) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.to(toVertex);
  }

  @Override
  default KillrVideoTraversal<S, E> from(Traversal<?, Vertex> fromVertex) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.from(fromVertex);
  }

  @Override
  default KillrVideoTraversal<S, E> to(Vertex toVertex) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.to(toVertex);
  }

  @Override
  default KillrVideoTraversal<S, E> from(Vertex fromVertex) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.from(fromVertex);
  }

  @Override
  default KillrVideoTraversal<S, Double> math(String expression) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.math(expression);
  }

  @Override
  default KillrVideoTraversal<S, E> filter(Predicate<Traverser<E>> predicate) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.filter(predicate);
  }

  @Override
  default KillrVideoTraversal<S, E> filter(Traversal<?, ?> filterTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.filter(filterTraversal);
  }

  @Override
  default KillrVideoTraversal<S, E> or(Traversal<?, ?>... orTraversals) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.or(orTraversals);
  }

  @Override
  default KillrVideoTraversal<S, E> and(Traversal<?, ?>... andTraversals) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.and(andTraversals);
  }

  @Override
  default KillrVideoTraversal<S, E> inject(E... injections) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.inject(injections);
  }

  @Override
  default KillrVideoTraversal<S, E> dedup(Scope scope, String... dedupLabels) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.dedup(scope,dedupLabels);
  }

  @Override
  default KillrVideoTraversal<S, E> dedup(String... dedupLabels) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.dedup(dedupLabels);
  }

  @Override
  default KillrVideoTraversal<S, E> where(String startKey, P<String> predicate) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.where(startKey,predicate);
  }

  @Override
  default KillrVideoTraversal<S, E> where(P<String> predicate) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.where(predicate);
  }

  @Override
  default KillrVideoTraversal<S, E> where(Traversal<?, ?> whereTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.where(whereTraversal);
  }

  @Override
  default KillrVideoTraversal<S, E> has(String propertyKey, P<?> predicate) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.has(propertyKey,predicate);
  }

  @Override
  default KillrVideoTraversal<S, E> has(T accessor, P<?> predicate) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.has(accessor,predicate);
  }

  @Override
  default KillrVideoTraversal<S, E> has(String propertyKey, Object value) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.has(propertyKey,value);
  }

  @Override
  default KillrVideoTraversal<S, E> has(T accessor, Object value) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.has(accessor,value);
  }

  @Override
  default KillrVideoTraversal<S, E> has(String label, String propertyKey, P<?> predicate) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.has(label,propertyKey,predicate);
  }

  @Override
  default KillrVideoTraversal<S, E> has(String label, String propertyKey, Object value) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.has(label,propertyKey,value);
  }

  @Override
  default KillrVideoTraversal<S, E> has(T accessor, Traversal<?, ?> propertyTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.has(accessor,propertyTraversal);
  }

  @Override
  default KillrVideoTraversal<S, E> has(String propertyKey, Traversal<?, ?> propertyTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.has(propertyKey,propertyTraversal);
  }

  @Override
  default KillrVideoTraversal<S, E> has(String propertyKey) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.has(propertyKey);
  }

  @Override
  default KillrVideoTraversal<S, E> hasNot(String propertyKey) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.hasNot(propertyKey);
  }

  @Override
  default KillrVideoTraversal<S, E> hasLabel(String label, String... otherLabels) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.hasLabel(label,otherLabels);
  }

  @Override
  default KillrVideoTraversal<S, E> hasLabel(P<String> predicate) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.hasLabel(predicate);
  }

  @Override
  default KillrVideoTraversal<S, E> hasId(Object id, Object... otherIds) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.hasId(id,otherIds);
  }

  @Override
  default KillrVideoTraversal<S, E> hasId(P<Object> predicate) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.hasId(predicate);
  }

  @Override
  default KillrVideoTraversal<S, E> hasKey(String label, String... otherLabels) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.hasKey(label,otherLabels);
  }

  @Override
  default KillrVideoTraversal<S, E> hasKey(P<String> predicate) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.hasKey(predicate);
  }

  @Override
  default KillrVideoTraversal<S, E> hasValue(Object value, Object... otherValues) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.hasValue(value,otherValues);
  }

  @Override
  default KillrVideoTraversal<S, E> hasValue(P<Object> predicate) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.hasValue(predicate);
  }

  @Override
  default KillrVideoTraversal<S, E> is(P<E> predicate) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.is(predicate);
  }

  @Override
  default KillrVideoTraversal<S, E> is(Object value) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.is(value);
  }

  @Override
  default KillrVideoTraversal<S, E> not(Traversal<?, ?> notTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.not(notTraversal);
  }

  @Override
  default KillrVideoTraversal<S, E> coin(double probability) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.coin(probability);
  }

  @Override
  default KillrVideoTraversal<S, E> range(long low, long high) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.range(low,high);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> range(Scope scope, long low, long high) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.range(scope,low,high);
  }

  @Override
  default KillrVideoTraversal<S, E> limit(long limit) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.limit(limit);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> limit(Scope scope, long limit) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.limit(scope,limit);
  }

  @Override
  default KillrVideoTraversal<S, E> tail() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.tail();
  }

  @Override
  default KillrVideoTraversal<S, E> tail(long limit) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.tail(limit);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> tail(Scope scope) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.tail(scope);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> tail(Scope scope, long limit) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.tail(scope,limit);
  }

  @Override
  default KillrVideoTraversal<S, E> skip(long skip) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.skip(skip);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> skip(Scope scope, long skip) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.skip(scope,skip);
  }

  @Override
  default KillrVideoTraversal<S, E> timeLimit(long timeLimit) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.timeLimit(timeLimit);
  }

  @Override
  default KillrVideoTraversal<S, E> simplePath() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.simplePath();
  }

  @Override
  default KillrVideoTraversal<S, E> cyclicPath() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.cyclicPath();
  }

  @Override
  default KillrVideoTraversal<S, E> sample(int amountToSample) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.sample(amountToSample);
  }

  @Override
  default KillrVideoTraversal<S, E> sample(Scope scope, int amountToSample) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.sample(scope,amountToSample);
  }

  @Override
  default KillrVideoTraversal<S, E> drop() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.drop();
  }

  @Override
  default KillrVideoTraversal<S, E> sideEffect(Consumer<Traverser<E>> consumer) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.sideEffect(consumer);
  }

  @Override
  default KillrVideoTraversal<S, E> sideEffect(Traversal<?, ?> sideEffectTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.sideEffect(sideEffectTraversal);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> cap(String sideEffectKey, String... sideEffectKeys) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.cap(sideEffectKey,sideEffectKeys);
  }

  @Override
  default KillrVideoTraversal<S, Edge> subgraph(String sideEffectKey) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.subgraph(sideEffectKey);
  }

  @Override
  default KillrVideoTraversal<S, E> aggregate(String sideEffectKey) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.aggregate(sideEffectKey);
  }

  @Override
  default KillrVideoTraversal<S, E> group(String sideEffectKey) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.group(sideEffectKey);
  }

  @Override
  default KillrVideoTraversal<S, E> groupCount(String sideEffectKey) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.groupCount(sideEffectKey);
  }

  @Override
  default KillrVideoTraversal<S, E> tree(String sideEffectKey) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.tree(sideEffectKey);
  }

  @Override
  default <V, U> KillrVideoTraversal<S, E> sack(BiFunction<V, U, V> sackOperator) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.sack(sackOperator);
  }

  @Override
  default KillrVideoTraversal<S, E> store(String sideEffectKey) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.store(sideEffectKey);
  }

  @Override
  default KillrVideoTraversal<S, E> profile(String sideEffectKey) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.profile(sideEffectKey);
  }

  @Override
  default KillrVideoTraversal<S, TraversalMetrics> profile() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.profile();
  }

  @Override
  default KillrVideoTraversal<S, E> property(VertexProperty.Cardinality cardinality, Object key,
      Object value, Object... keyValues) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.property(cardinality,key,value,keyValues);
  }

  @Override
  default KillrVideoTraversal<S, E> property(Object key, Object value, Object... keyValues) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.property(key,value,keyValues);
  }

  @Override
  default <M, E2> KillrVideoTraversal<S, E2> branch(Traversal<?, M> branchTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.branch(branchTraversal);
  }

  @Override
  default <M, E2> KillrVideoTraversal<S, E2> branch(Function<Traverser<E>, M> function) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.branch(function);
  }

  @Override
  default <M, E2> KillrVideoTraversal<S, E2> choose(Traversal<?, M> choiceTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.choose(choiceTraversal);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> choose(Traversal<?, ?> traversalPredicate,
      Traversal<?, E2> trueChoice, Traversal<?, E2> falseChoice) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.choose(traversalPredicate,trueChoice,falseChoice);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> choose(Traversal<?, ?> traversalPredicate,
      Traversal<?, E2> trueChoice) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.choose(traversalPredicate,trueChoice);
  }

  @Override
  default <M, E2> KillrVideoTraversal<S, E2> choose(Function<E, M> choiceFunction) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.choose(choiceFunction);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> choose(Predicate<E> choosePredicate,
      Traversal<?, E2> trueChoice, Traversal<?, E2> falseChoice) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.choose(choosePredicate,trueChoice,falseChoice);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> choose(Predicate<E> choosePredicate,
      Traversal<?, E2> trueChoice) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.choose(choosePredicate,trueChoice);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> optional(Traversal<?, E2> optionalTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.optional(optionalTraversal);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> union(Traversal<?, E2>... unionTraversals) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.union(unionTraversals);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> coalesce(Traversal<?, E2>... coalesceTraversals) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.coalesce(coalesceTraversals);
  }

  @Override
  default KillrVideoTraversal<S, E> repeat(Traversal<?, E> repeatTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.repeat(repeatTraversal);
  }

  @Override
  default KillrVideoTraversal<S, E> repeat(String loopName, Traversal<?, E> repeatTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.repeat(loopName,repeatTraversal);
  }

  @Override
  default KillrVideoTraversal<S, E> emit(Traversal<?, ?> emitTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.emit(emitTraversal);
  }

  @Override
  default KillrVideoTraversal<S, E> emit(Predicate<Traverser<E>> emitPredicate) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.emit(emitPredicate);
  }

  @Override
  default KillrVideoTraversal<S, E> emit() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.emit();
  }

  @Override
  default KillrVideoTraversal<S, E> until(Traversal<?, ?> untilTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.until(untilTraversal);
  }

  @Override
  default KillrVideoTraversal<S, E> until(Predicate<Traverser<E>> untilPredicate) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.until(untilPredicate);
  }

  @Override
  default KillrVideoTraversal<S, E> times(int maxLoops) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.times(maxLoops);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> local(Traversal<?, E2> localTraversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.local(localTraversal);
  }

  @Override
  default KillrVideoTraversal<S, E> pageRank() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.pageRank();
  }

  @Override
  default KillrVideoTraversal<S, E> pageRank(double alpha) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.pageRank(alpha);
  }

  @Override
  default KillrVideoTraversal<S, E> peerPressure() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.peerPressure();
  }

  @Override
  default KillrVideoTraversal<S, E> connectedComponent() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.connectedComponent();
  }

  @Override
  default KillrVideoTraversal<S, Path> shortestPath() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.shortestPath();
  }

  @Override
  default KillrVideoTraversal<S, E> program(VertexProgram<?> vertexProgram) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.program(vertexProgram);
  }

  @Override
  default KillrVideoTraversal<S, E> as(String stepLabel, String... stepLabels) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.as(stepLabel,stepLabels);
  }

  @Override
  default KillrVideoTraversal<S, E> barrier() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.barrier();
  }

  @Override
  default KillrVideoTraversal<S, E> barrier(int maxBarrierSize) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.barrier(maxBarrierSize);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E2> index() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.index();
  }

  @Override
  default KillrVideoTraversal<S, E> barrier(Consumer<TraverserSet<Object>> barrierConsumer) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.barrier(barrierConsumer);
  }

  @Override
  default KillrVideoTraversal<S, E> with(String key) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.with(key);
  }

  @Override
  default KillrVideoTraversal<S, E> with(String key, Object value) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.with(key,value);
  }

  @Override
  default KillrVideoTraversal<S, E> by() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.by();
  }

  @Override
  default KillrVideoTraversal<S, E> by(Traversal<?, ?> traversal) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.by(traversal);
  }

  @Override
  default KillrVideoTraversal<S, E> by(T token) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.by(token);
  }

  @Override
  default KillrVideoTraversal<S, E> by(String key) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.by(key);
  }

  @Override
  default <V> KillrVideoTraversal<S, E> by(Function<V, Object> function) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.by(function);
  }

  @Override
  default <V> KillrVideoTraversal<S, E> by(Traversal<?, ?> traversal, Comparator<V> comparator) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.by(traversal,comparator);
  }

  @Override
  default KillrVideoTraversal<S, E> by(Comparator<E> comparator) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.by(comparator);
  }

  @Override
  default KillrVideoTraversal<S, E> by(Order order) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.by(order);
  }

  @Override
  default <V> KillrVideoTraversal<S, E> by(String key, Comparator<V> comparator) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.by(key,comparator);
  }

  @Override
  default <U> KillrVideoTraversal<S, E> by(Function<U, Object> function, Comparator comparator) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.by(function,comparator);
  }

  @Override
  default <M, E2> KillrVideoTraversal<S, E> option(M pickToken, Traversal<?, E2> traversalOption) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.option(pickToken,traversalOption);
  }

  @Override
  default <E2> KillrVideoTraversal<S, E> option(Traversal<?, E2> traversalOption) {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.option(traversalOption);
  }

  @Override
  default KillrVideoTraversal<S, E> read() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.read();
  }

  @Override
  default KillrVideoTraversal<S, E> write() {
    return (KillrVideoTraversal) KillrVideoTraversalDsl.super.write();
  }

  @Override
  default KillrVideoTraversal<S, E> iterate() {
    KillrVideoTraversalDsl.super.iterate();
    return this;
  }
}
