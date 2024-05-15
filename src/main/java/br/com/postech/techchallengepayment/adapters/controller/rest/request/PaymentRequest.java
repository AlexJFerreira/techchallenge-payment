package br.com.postech.techchallengepayment.adapters.controller.rest.request;

import lombok.Data;

@Data
public class PaymentRequest {
  private final String orderId;
  private final String cpf;
}
