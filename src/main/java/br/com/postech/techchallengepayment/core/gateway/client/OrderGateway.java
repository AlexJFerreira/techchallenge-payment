package br.com.postech.techchallengepayment.core.gateway.client;


public interface OrderGateway {
  void changeOrderStatus(Integer orderId);
}
