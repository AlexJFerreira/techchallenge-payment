package br.com.postech.techchallengepayment.adapters.controller.rest.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentCreationResponse {
  private String orderId;
  private String id;
  private BigDecimal amount;
}
