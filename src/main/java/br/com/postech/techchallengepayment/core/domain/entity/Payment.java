package br.com.postech.techchallengepayment.core.domain.entity;

import br.com.postech.techchallengepayment.core.domain.enums.PaymentStatus;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class Payment {
  private String id;
  private String orderId;
  private String cpf;
  private PaymentStatus status;
  private BigDecimal amount;
}
