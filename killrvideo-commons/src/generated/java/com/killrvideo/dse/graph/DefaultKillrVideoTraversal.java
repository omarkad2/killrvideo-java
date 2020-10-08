package com.killrvideo.dse.graph;

import java.lang.Override;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.util.DefaultTraversal;
import org.apache.tinkerpop.gremlin.structure.Graph;

public class DefaultKillrVideoTraversal<S, E> extends DefaultTraversal<S, E> implements KillrVideoTraversal<S, E> {
  public DefaultKillrVideoTraversal() {
    super();
  }

  public DefaultKillrVideoTraversal(Graph graph) {
    super(graph);
  }

  public DefaultKillrVideoTraversal(KillrVideoTraversalSource traversalSource) {
    super(traversalSource);
  }

  public DefaultKillrVideoTraversal(KillrVideoTraversalSource traversalSource,
      GraphTraversal.Admin traversal) {
    super(traversalSource, traversal.asAdmin());
  }

  @Override
  public KillrVideoTraversal<S, E> iterate() {
    return (KillrVideoTraversal) super.iterate();
  }

  @Override
  public GraphTraversal.Admin<S, E> asAdmin() {
    return (GraphTraversal.Admin) super.asAdmin();
  }

  @Override
  public DefaultKillrVideoTraversal<S, E> clone() {
    return (DefaultKillrVideoTraversal) super.clone();
  }
}
