package co.com.ies.pagination.infrastructure.primary;

import static co.com.ies.BeanValidationAssertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.com.ies.UnitTest;
import co.com.ies.pagination.domain.MicrolitePageable;

@UnitTest
class RestMicrolitePageableTest {

  @Test
  void shouldConvertToDomain() {
    MicrolitePageable pageable = pageable().toPageable();

    assertThat(pageable.page()).isEqualTo(1);
    assertThat(pageable.pageSize()).isEqualTo(15);
  }

  @Test
  void shouldNotValidateWithPageUnderZero() {
    RestMicrolitePageable pageable = pageable();
    pageable.setPage(-1);

    assertThatBean(pageable).hasInvalidProperty("page");
  }

  @Test
  void shouldNotValidateWithSizeAtZero() {
    RestMicrolitePageable pageable = pageable();
    pageable.setPageSize(0);

    assertThatBean(pageable).hasInvalidProperty("pageSize").withParameter("value", 1L);
  }

  @Test
  void shouldNotValidateWithPageSizeOverHundred() {
    RestMicrolitePageable pageable = pageable();
    pageable.setPageSize(101);

    assertThatBean(pageable).hasInvalidProperty("pageSize");
  }

  private RestMicrolitePageable pageable() {
    return new RestMicrolitePageable(1, 15);
  }
}
