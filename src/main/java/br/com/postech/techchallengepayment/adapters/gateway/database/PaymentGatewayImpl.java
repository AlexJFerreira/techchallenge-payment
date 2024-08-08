package br.com.postech.techchallengepayment.adapters.gateway.database;


import br.com.postech.techchallengepayment.adapters.gateway.database.entity.PaymentEntity;
import br.com.postech.techchallengepayment.adapters.gateway.database.repository.PaymentRepository;
import br.com.postech.techchallengepayment.core.domain.entity.Payment;
import br.com.postech.techchallengepayment.core.domain.enums.PaymentStatus;
import br.com.postech.techchallengepayment.core.gateway.database.PaymentGateway;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PaymentGatewayImpl implements PaymentGateway {

  private final PaymentRepository paymentRepository;
  private final ModelMapper modelMapper;

  @Override
  @Transactional
  public Optional<Payment> approvePayment(String paymentId, PaymentStatus paymentStatus) {
    Optional<PaymentEntity> payment = paymentRepository.findById(paymentId);
    payment.ifPresent(paymentEntity -> {
      paymentEntity.changeStatus(paymentStatus);
      paymentRepository.save(paymentEntity);
    });

    return payment.map(paymentEntity -> modelMapper.map(paymentEntity, Payment.class));
  }

  @Override
  public Optional<Payment> searchPaymentByOrderId(Integer orderId) {
    return paymentRepository.findByOrderId(orderId)
        .map(paymentEntity -> modelMapper.map(paymentEntity, Payment.class));
  }

  @Override
  public Payment createPayment(Payment payment) {
    PaymentEntity paymentEntity = modelMapper.map(payment, PaymentEntity.class);
    return modelMapper.map(paymentRepository.save(paymentEntity), Payment.class);
  }
}
