package co.com.ies.pagination.infrastructure.secondary;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import co.com.ies.error.domain.Assert;
import co.com.ies.pagination.domain.MicrolitePage;
import co.com.ies.pagination.domain.MicrolitePageable;

public final class MicrolitePages {

  private MicrolitePages() {}

  public static Pageable from(MicrolitePageable pagination) {
    return from(pagination, Sort.unsorted());
  }

  public static Pageable from(MicrolitePageable pagination, Sort sort) {
    Assert.notNull("pagination", pagination);
    Assert.notNull("sort", sort);

    return PageRequest.of(pagination.page(), pagination.pageSize(), sort);
  }

  public static <S, T> MicrolitePage<T> from(Page<S> springPage, Function<S, T> mapper) {
    Assert.notNull("springPage", springPage);
    Assert.notNull("mapper", mapper);

    return MicrolitePage
      .builder(springPage.getContent().parallelStream().map(mapper).toList())
      .currentPage(springPage.getNumber())
      .pageSize(springPage.getSize())
      .totalElementsCount(springPage.getTotalElements())
      .build();
  }
}
