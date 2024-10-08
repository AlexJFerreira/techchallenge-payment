package br.com.postech.techchallengepayment.infra;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "events")
public class EventsConfig {
  private String paymentsCreationQueue;
  private String paymentsApprovalQueue;
  private String ordersQueue;
}
