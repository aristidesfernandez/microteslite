package co.com.ies.account.domain;

public interface TokensRepository {
  Token buildToken(AuthenticationQuery query);
}
