package br.com.postech.techchallengepayment.adapters.gateway.database.entity;


import br.com.postech.techchallengepayment.core.domain.enums.PaymentStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("techchallenge-payments")
@Data
public class PaymentEntity {

  @Id
  private Integer id;


  private Integer orderId;

  private PaymentStatus status = PaymentStatus.PENDING;

  public void approve() {
    this.status = PaymentStatus.APPROVED;
  }
}