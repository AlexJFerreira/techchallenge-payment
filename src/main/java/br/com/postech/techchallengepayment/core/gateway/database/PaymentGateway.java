package br.com.postech.techchallengepayment.core.gateway.database;


import br.com.postech.techchallengepayment.core.domain.entity.Payment;
import br.com.postech.techchallengepayment.core.domain.enums.PaymentStatus;
import java.util.Optional;

public interface PaymentGateway {
  Optional<Payment> approvePayment(String paymentId, PaymentStatus paymentStatus);
  Optional<Payment> searchPaymentByOrderId(Integer orderId);
  Payment createPayment(Payment payment);
}
