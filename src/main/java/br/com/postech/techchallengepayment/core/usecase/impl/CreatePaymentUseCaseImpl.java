package br.com.postech.techchallengepayment.core.usecase.impl;

import br.com.postech.techchallengepayment.core.domain.entity.Payment;
import br.com.postech.techchallengepayment.core.gateway.database.PaymentGateway;
import br.com.postech.techchallengepayment.core.usecase.CreatePaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatePaymentUseCaseImpl implements CreatePaymentUseCase {

  private final PaymentGateway paymentGateway;

  @Override
  public Payment execute(Payment payment) {
    return paymentGateway.createPayment(payment);
  }
}
