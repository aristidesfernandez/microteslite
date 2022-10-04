package co.com.ies.account.domain;

import co.com.ies.error.domain.Assert;

public record Token(String token) {
  public Token {
    Assert.notBlank("token", token);
  }

  public String get() {
    return token();
  }

  public String bearer() {
    return "Bearer " + token();
  }
}
