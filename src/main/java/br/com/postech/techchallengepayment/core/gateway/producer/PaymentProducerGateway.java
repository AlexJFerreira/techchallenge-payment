package br.com.postech.techchallengepayment.core.gateway.producer;

import br.com.postech.techchallengepayment.core.domain.entity.Payment;
import br.com.postech.techchallengepayment.core.domain.enums.PaymentStatus;

public interface PaymentProducerGateway {
  void notifyPaymentCreationQueue(Payment payment);

  void notifyPaymentApprovalQueue(String paymentId, PaymentStatus paymentStatus);
}
