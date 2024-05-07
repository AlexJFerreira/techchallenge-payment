package br.com.postech.techchallengepayment.core.domain.entity;

import br.com.postech.techchallengepayment.core.domain.enums.PaymentStatus;
import lombok.Data;

@Data
public class Payment {
  private Integer id;
  private Integer orderId;
}
