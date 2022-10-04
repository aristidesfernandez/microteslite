package co.com.ies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import co.com.ies.common.domain.Generated;

@SpringBootApplication
@Generated(reason = "Not testing logs")
public class MicroliteApp {

  private static final Logger log = LoggerFactory.getLogger(MicroliteApp.class);

  public static void main(String[] args) {
    Environment env = SpringApplication.run(MicroliteApp.class, args).getEnvironment();

    if(log.isInfoEnabled()) {
      log.info(ApplicationStartupTraces.of(env));
    }
  }
}
