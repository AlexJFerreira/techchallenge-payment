package br.com.postech.techchallengepayment.adapters.gateway.client;

import br.com.postech.techchallengepayment.adapters.gateway.client.request.OrderRequest;
import br.com.postech.techchallengepayment.adapters.gateway.client.request.OrderStatus;
import br.com.postech.techchallengepayment.core.gateway.client.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderGatewayImpl implements OrderGateway {

  private final OrderClient orderClient;
  @Override
  public void changeOrderStatus(Integer orderId) {
    orderClient.changeOrderStatus(new OrderRequest(OrderStatus.PREPARING), orderId);
  }
}
