package br.com.postech.techchallengepayment.core.usecase.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.postech.techchallengepayment.core.domain.entity.Payment;
import br.com.postech.techchallengepayment.core.gateway.client.OrderGateway;
import br.com.postech.techchallengepayment.core.gateway.database.PaymentGateway;
import br.com.postech.techchallengepayment.util.PaymentTestProvider;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SearchPaymentByOrderIdUseCaseImplTest extends PaymentTestProvider {

  @InjectMocks
  private SearchPaymentByOrderIdUseCaseImpl searchPaymentByOrderIdUseCase;

  @Mock
  private PaymentGateway paymentGateway;

  @Test
  void execute() {
    //Arrange
    var orderId = 1;
    var outPutPayment = getFakeOutputPayment();

    when(paymentGateway.searchPaymentByOrderId(orderId))
        .thenReturn(Optional.of(outPutPayment));

    //Act
    Payment payment = searchPaymentByOrderIdUseCase.execute(orderId);

    //Act
    verify(paymentGateway).searchPaymentByOrderId(orderId);
    assertNotNull(payment);
  }
}