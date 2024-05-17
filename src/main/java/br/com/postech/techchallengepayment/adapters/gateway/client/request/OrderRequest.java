package br.com.postech.techchallengepayment.adapters.gateway.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderRequest {
  private OrderStatus orderStatus;

}
