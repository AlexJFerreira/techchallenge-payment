package br.com.postech.techchallengepayment.core.usecase.impl;

import br.com.postech.techchallengepayment.adapters.controller.rest.response.PaymentCreationResponse;
import br.com.postech.techchallengepayment.adapters.gateway.producer.PaymentProducerGatewayImpl;
import br.com.postech.techchallengepayment.core.domain.entity.Payment;
import br.com.postech.techchallengepayment.core.gateway.database.PaymentGateway;
import br.com.postech.techchallengepayment.core.gateway.producer.PaymentProducerGateway;
import br.com.postech.techchallengepayment.core.usecase.CreatePaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatePaymentUseCaseImpl implements CreatePaymentUseCase {

  private final PaymentGateway paymentGateway;
  private final PaymentProducerGateway paymentProducerGateway;

  @Override
  public Payment execute(Payment payment) {
    Payment paymentResponse = paymentGateway.createPayment(payment);
    paymentProducerGateway.notifyPaymentCreationQueue(paymentResponse);
    return paymentResponse;
  }
}
