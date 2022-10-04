package co.com.ies.technical.infrastructure.primary.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import co.com.ies.UnitTest;

@UnitTest
class HeaderUtilTest {

  @Test
  void shouldCreateFailureAlertWithTranslation() {
    HttpHeaders headers = HeaderUtil.createFailureAlert("myApp", "User", "404");
    
    assertThat(headers.getFirst("X-myApp-error")).isEqualTo("error.404");
    assertThat(headers.getFirst("X-myApp-params")).isEqualTo("User");
  }
}
