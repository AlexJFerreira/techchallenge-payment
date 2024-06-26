package br.com.postech.techchallengepayment.core.usecase.impl;


import br.com.postech.techchallengepayment.core.domain.entity.Payment;
import br.com.postech.techchallengepayment.core.exceptions.NotFoundException;
import br.com.postech.techchallengepayment.core.gateway.client.OrderGateway;
import br.com.postech.techchallengepayment.core.gateway.database.PaymentGateway;
import br.com.postech.techchallengepayment.core.usecase.ApprovePaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApprovePaymentUseCaseImpl implements ApprovePaymentUseCase {

  private final PaymentGateway paymentGateway;
  private final OrderGateway orderGateway;

  @Override
  public Payment execute(String paymentId) {
    Payment payment = paymentGateway.approvePayment(paymentId)
        .orElseThrow(() -> new NotFoundException(String.format("Payment with id %s not found", paymentId)));

    orderGateway.changeOrderStatus(Integer.valueOf(payment.getOrderId()));
    return payment;
  }
}
