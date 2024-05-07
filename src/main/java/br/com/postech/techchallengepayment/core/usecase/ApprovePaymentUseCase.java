package br.com.postech.techchallengepayment.core.usecase;


import br.com.postech.techchallengepayment.core.domain.entity.Payment;

public interface ApprovePaymentUseCase {
  Payment execute(Integer paymentId);
}
