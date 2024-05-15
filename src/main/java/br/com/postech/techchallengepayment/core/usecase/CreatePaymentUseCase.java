package br.com.postech.techchallengepayment.core.usecase;

import br.com.postech.techchallengepayment.core.domain.entity.Payment;

public interface CreatePaymentUseCase {
  Payment execute(Payment payment);
}
