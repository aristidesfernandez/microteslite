package co.com.ies.account.application;

import co.com.ies.account.domain.AuthenticationQuery;
import co.com.ies.account.domain.Token;
import co.com.ies.account.domain.TokensRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountApplicationService {

  private final TokensRepository tokens;

  public AccountApplicationService(TokensRepository tokens) {
    this.tokens = tokens;
  }

  public Token createToken(AuthenticationQuery query) {
    return tokens.buildToken(query);
  }
}
