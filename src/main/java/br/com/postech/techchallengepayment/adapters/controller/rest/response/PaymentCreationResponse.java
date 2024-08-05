package br.com.postech.techchallengepayment.adapters.controller.rest.response;

import br.com.postech.techchallengepayment.core.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentCreationResponse {
  @JsonProperty("order_id")
  private String orderId;
  private String id;
  private BigDecimal amount;
  private PaymentStatus status;
}
