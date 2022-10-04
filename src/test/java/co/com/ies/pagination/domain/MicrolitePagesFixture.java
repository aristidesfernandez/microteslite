package co.com.ies.pagination.domain;

import java.util.List;

import co.com.ies.pagination.domain.MicrolitePage.MicrolitePageBuilder;

public final class MicrolitePagesFixture {

  private MicrolitePagesFixture() {}

  public static MicrolitePage<String> page() {
    return pageBuilder().build();
  }

  public static MicrolitePageBuilder<String> pageBuilder() {
    return MicrolitePage.builder(List.of("test")).currentPage(2).pageSize(10).totalElementsCount(21);
  }
}
