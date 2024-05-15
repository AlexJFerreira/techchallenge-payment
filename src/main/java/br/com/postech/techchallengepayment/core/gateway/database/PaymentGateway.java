package br.com.postech.techchallengepayment.core.gateway.database;


import br.com.postech.techchallengepayment.core.domain.entity.Payment;
import java.util.Optional;

public interface PaymentGateway {
  Optional<Payment> approvePayment(String paymentId);
  Optional<Payment> searchPaymentByOrderId(Integer orderId);
  Payment createPayment(Payment payment);
}
