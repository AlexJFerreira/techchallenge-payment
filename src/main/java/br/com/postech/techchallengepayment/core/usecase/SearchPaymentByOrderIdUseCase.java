package br.com.postech.techchallengepayment.core.usecase;


import br.com.postech.techchallengepayment.core.domain.entity.Payment;

public interface SearchPaymentByOrderIdUseCase {
  Payment execute(Integer orderId);
}
