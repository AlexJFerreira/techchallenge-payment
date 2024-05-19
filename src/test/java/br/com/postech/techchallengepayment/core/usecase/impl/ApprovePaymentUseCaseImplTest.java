package br.com.postech.techchallengepayment.core.usecase.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.postech.techchallengepayment.core.domain.entity.Payment;
import br.com.postech.techchallengepayment.core.exceptions.NotFoundException;
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
class ApprovePaymentUseCaseImplTest extends PaymentTestProvider {

  @InjectMocks
  private ApprovePaymentUseCaseImpl approvePaymentUseCase;

  @Mock
  private PaymentGateway paymentGateway;

  @Mock
  private OrderGateway orderGateway;

  @Test
  void executeWithSuccess() {
    //Arrange
    var paymentId = "7203e682-a02c-4f6e-b555-c39f892c9f0c";
    var outPutPayment = getFakeOutputPayment();
    when(paymentGateway.approvePayment(paymentId))
        .thenReturn(Optional.of(outPutPayment));

    doNothing()
        .when(orderGateway).changeOrderStatus(anyInt());

    //Act
    Payment payment = approvePaymentUseCase.execute(paymentId);

    //Assert
    verify(paymentGateway).approvePayment(paymentId);
    verify(orderGateway).changeOrderStatus(anyInt());
    assertNotNull(payment);
  }

  @Test
  void executeWhenPaymentIsNotFoundThenThrowsNotFoundException() {
    //Arrange
    var paymentId = "7203e682-a02c-4f6e-b555-c39f892c9f0c";
    when(paymentGateway.approvePayment(paymentId))
        .thenReturn(Optional.empty());

    //Act
    assertThrows(NotFoundException.class, () -> approvePaymentUseCase.execute(paymentId));

    //Assert
    verify(paymentGateway).approvePayment(paymentId);
    verify(orderGateway, never()).changeOrderStatus(anyInt());
  }
}