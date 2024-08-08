package br.com.postech.techchallengepayment.core.usecase;


import br.com.postech.techchallengepayment.core.domain.entity.Payment;
import br.com.postech.techchallengepayment.core.domain.enums.PaymentStatus;

public interface ApprovePaymentUseCase {
  Payment execute(String paymentId, PaymentStatus paymentStatus);
}
