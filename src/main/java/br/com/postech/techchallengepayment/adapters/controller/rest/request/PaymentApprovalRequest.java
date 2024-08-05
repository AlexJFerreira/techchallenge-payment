package br.com.postech.techchallengepayment.adapters.controller.rest.request;

import br.com.postech.techchallengepayment.core.domain.enums.PaymentStatus;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentApprovalRequest {
  private final String paymentId;
  private final PaymentStatus status;
}