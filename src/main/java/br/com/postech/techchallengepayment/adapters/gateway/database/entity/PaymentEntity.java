package br.com.postech.techchallengepayment.adapters.gateway.database.entity;


import br.com.postech.techchallengepayment.core.domain.enums.PaymentStatus;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("payments")
@Data
public class PaymentEntity {

  @Id
  private String id = UUID.randomUUID().toString();
  private Integer orderId;
  private String cpf;
  private BigDecimal amount;


  private PaymentStatus status = PaymentStatus.PENDING;

  public void approve() {
    this.status = PaymentStatus.APPROVED;
  }
}