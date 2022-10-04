package co.com.ies.pagination.domain;

import java.util.List;
import java.util.function.Function;

import co.com.ies.common.domain.MicroliteCollections;
import co.com.ies.error.domain.Assert;

public class MicrolitePage<T> {

  private static final int MINIMAL_PAGE_COUNT = 1;
  
  private final List<T> content;
  private final int currentPage;
  private final int pageSize;
  private final long totalElementsCount;

  private MicrolitePage(MicrolitePageBuilder<T> builder) {
    content = MicroliteCollections.immutable(builder.content);
    currentPage = builder.currentPage;
    pageSize = buildPageSize(builder.pageSize);
    totalElementsCount = buildTotalElementsCount(builder.totalElementsCount);
  }

  private int buildPageSize(int pageSize) {
    if (pageSize == -1) {
      return content.size();
    }

    return pageSize;
  }

  private long buildTotalElementsCount(long totalElementsCount) {
    if (totalElementsCount == -1) {
      return content.size();
    }

    return totalElementsCount;
  }

  public static <T> MicrolitePage<T> singlePage(List<T> content) {
    return builder(content).build();
  }

  public static <T> MicrolitePageBuilder<T> builder(List<T> content) {
    return new MicrolitePageBuilder<>(content);
  }

  public static <T> MicrolitePage<T> of(List<T> elements, MicrolitePageable pagination) {
    Assert.notNull("elements", elements);
    Assert.notNull("pagination", pagination);

    List<T> content = elements.subList(
      Math.min(pagination.offset(), elements.size()),
      Math.min(pagination.offset() + pagination.pageSize(), elements.size())
    );

    return builder(content).currentPage(pagination.page()).pageSize(pagination.pageSize()).totalElementsCount(elements.size()).build();
  }

  public List<T> content() {
    return content;
  }

  public int currentPage() {
    return currentPage;
  }

  public int pageSize() {
    return pageSize;
  }

  public long totalElementsCount() {
    return totalElementsCount;
  }

  public int pageCount() {
    if (totalElementsCount > 0) {
      return (int) Math.ceil(totalElementsCount / (float) pageSize);
    }

    return MINIMAL_PAGE_COUNT;
  }

  public boolean hasPrevious() {
    return currentPage > 0;
  }

  public boolean hasNext() {
    return isNotLast();
  }

  public boolean isNotLast() {
    return currentPage + 1 < pageCount();
  }

  public <R> MicrolitePage<R> map(Function<T, R> mapper) {
    Assert.notNull("mapper", mapper);

    return builder(content().stream().map(mapper).toList())
      .currentPage(currentPage)
      .pageSize(pageSize)
      .totalElementsCount(totalElementsCount)
      .build();
  }

  public static class MicrolitePageBuilder<T> {

    private final List<T> content;
    private int currentPage;
    private int pageSize = -1;
    private long totalElementsCount = -1;

    private MicrolitePageBuilder(List<T> content) {
      this.content = content;
    }

    public MicrolitePageBuilder<T> pageSize(int pageSize) {
      this.pageSize = pageSize;

      return this;
    }

    public MicrolitePageBuilder<T> currentPage(int currentPage) {
      this.currentPage = currentPage;

      return this;
    }

    public MicrolitePageBuilder<T> totalElementsCount(long totalElementsCount) {
      this.totalElementsCount = totalElementsCount;

      return this;
    }

    public MicrolitePage<T> build() {
      return new MicrolitePage<>(this);
    }
  }
}
