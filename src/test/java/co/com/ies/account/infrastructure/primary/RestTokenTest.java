package co.com.ies.account.infrastructure.primary;

import static co.com.ies.account.domain.TokensFixture.*;
import static org.assertj.core.api.Assertions.*;

import co.com.ies.JsonHelper;
import co.com.ies.UnitTest;
import org.junit.jupiter.api.Test;

@UnitTest
class RestTokenTest {

  @Test
  void shouldConvertFromDomain() {
    assertThat(JsonHelper.writeAsString(RestToken.from(token()))).isEqualTo(json());
  }

  private String json() {
    return "{\"id_token\":\"token\"}";
  }
}
