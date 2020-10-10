package com.killrvideo.dse.dto;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Ease usage of the paginState.
 *
 * @author DataStax Developer Advocates team.
 */
public class ResultListPage < ENTITY > {

  /** Results map as entities. */
  private List<ENTITY> listOfResults = new ArrayList<>();

  /** Custom management of paging state. */
  private Optional< String > nextPage = Optional.empty();

  /**
   * Default Constructor.
   */
  public ResultListPage() {}

  public ResultListPage(List<ENTITY> results, ByteBuffer nextPage) {
    this.listOfResults = results;
    this.nextPage = Optional.ofNullable(nextPage)
                       .map(ByteBuffer::toString);
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (null != listOfResults) {
      sb.append("Results:");
      sb.append(listOfResults.toString());
    }
    if (nextPage.isPresent()) {
      sb.append("\n + pagingState is present : ");
      sb.append(nextPage.get());
    }
    return sb.toString();
  }

  /**
   * Getter for attribute 'listOfResults'.
   *
   * @return current value of 'comments'
   */
  public List<ENTITY> getResults() {
    return listOfResults;
  }

  /**
   * Setter for attribute 'listOfResults'.
   *
   * @param comments
   *            new value for 'comments '
   */
  public void setresults(List<ENTITY> comments) {
    this.listOfResults = comments;
  }

  /**
   * Getter for attribute 'listOfResults'.
   *
   * @return current value of 'pagingState'
   */
  public Optional<String> getPagingState() {
    return nextPage;
  }

  /**
   * Setter for attribute 'pagingState'.
   *
   * @param pagingState
   *            new value for 'pagingState '
   */
  public void setPagingState(Optional<String> pagingState) {
    this.nextPage = pagingState;
  }

}
