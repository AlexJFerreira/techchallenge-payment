package br.com.postech.techchallengepayment.adapters.gateway.producer.request;

import br.com.postech.techchallengepayment.core.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentApprovalMessage {
  @JsonProperty("order_id")
  String orderId;

  @JsonProperty("payment_status")
  PaymentStatus paymentStatus;
}
