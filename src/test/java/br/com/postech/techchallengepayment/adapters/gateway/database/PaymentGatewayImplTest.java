package br.com.postech.techchallengepayment.adapters.gateway.database;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.postech.techchallengepayment.adapters.gateway.client.OrderClient;
import br.com.postech.techchallengepayment.adapters.gateway.client.OrderGatewayImpl;
import br.com.postech.techchallengepayment.adapters.gateway.database.repository.PaymentRepository;
import br.com.postech.techchallengepayment.core.domain.entity.Payment;
import br.com.postech.techchallengepayment.infra.ModelMapperConfig;
import br.com.postech.techchallengepayment.util.PaymentTestProvider;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class PaymentGatewayImplTest extends PaymentTestProvider {

  @InjectMocks
  private PaymentGatewayImpl paymentGateway;

  @Mock
  private PaymentRepository paymentRepository;

  @BeforeEach
  public void beforeEach() {
    ReflectionTestUtils.setField(paymentGateway, "modelMapper", new ModelMapperConfig().modelMapper());
  }

  @Test
  void approvePayment() {
    //Arrange
    var paymentId = "7203e682-a02c-4f6e-b555-c39f892c9f0c";
    var inputPaymentEntity = getFakeInputPaymentEntity();
    var outputPaymentEntity = getFakeOutputPaymentEntity();

    when(paymentRepository.findById(paymentId))
        .thenReturn(Optional.of(inputPaymentEntity));

    when(paymentRepository.save(inputPaymentEntity))
        .thenReturn(outputPaymentEntity);

    //Act
    Optional<Payment> payment = paymentGateway.approvePayment(paymentId);

    //Assert
    verify(paymentRepository).findById(paymentId);
    verify(paymentRepository).save(inputPaymentEntity);
    assertTrue(payment.isPresent());
  }

  @Test
  void searchPaymentByOrderId() {
    //Arrange
    var orderIdToSearch = 1;
    var outputPaymentEntity = getFakeOutputPaymentEntity();

    when(paymentRepository.findByOrderId(orderIdToSearch))
        .thenReturn(Optional.of(outputPaymentEntity));

    //Act
    Optional<Payment> payment = paymentGateway.searchPaymentByOrderId(orderIdToSearch);

    //Assert
    verify(paymentRepository).findByOrderId(orderIdToSearch);
    assertTrue(payment.isPresent());

  }

  @Test
  void createPayment() {
    //Arrange
    var inputPayment = getFakeInputPayment();
    var outputPaymentEntity = getFakeOutputPaymentEntity();

    when(paymentRepository.save(any()))
        .thenReturn(outputPaymentEntity);

    //Act
    Payment payment = paymentGateway.createPayment(inputPayment);

    //Assert
    assertNotNull(payment);
    verify(paymentRepository).save(any());
  }
}