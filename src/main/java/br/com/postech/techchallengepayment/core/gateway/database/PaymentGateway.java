package br.com.postech.techchallengepayment.core.gateway.database;


import br.com.postech.techchallengepayment.core.domain.entity.Payment;
import java.util.Optional;

public interface PaymentGateway {
  Optional<Payment> approvePayment(Integer paymentId);
  Optional<Payment> searchPaymentByOrderId(Integer orderId);

}
