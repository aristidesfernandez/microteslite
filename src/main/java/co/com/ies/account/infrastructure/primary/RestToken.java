package co.com.ies.account.infrastructure.primary;

import com.fasterxml.jackson.annotation.JsonProperty;
import co.com.ies.account.domain.Token;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "token", description = "A JWT token")
class RestToken {

  private String idToken;

  private RestToken(String idToken) {
    this.idToken = idToken;
  }

  static RestToken from(Token token) {
    return new RestToken(token.get());
  }

  @Schema(description = "JWT token", required = true)
  @JsonProperty("id_token")
  String getIdToken() {
    return idToken;
  }
}
