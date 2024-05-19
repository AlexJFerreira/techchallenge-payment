package br.com.postech.techchallengepayment.adapters.gateway.client;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.postech.techchallengepayment.adapters.gateway.client.request.OrderRequest;
import br.com.postech.techchallengepayment.adapters.gateway.client.request.OrderStatus;
import br.com.postech.techchallengepayment.util.PaymentTestProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderGatewayImplTest extends PaymentTestProvider {

  @InjectMocks
  private OrderGatewayImpl OrderGatewayImpl;

  @Mock
  private OrderClient orderClient;

  @Test
  void changeOrderStatusWithSuccess() {
    //Arrange
    var orderId = 1;
    var orderResponse = getFakeOrderResponse();

    when(orderClient.changeOrderStatus(any(), anyInt()))
        .thenReturn(orderResponse);

    //Act
    OrderGatewayImpl.changeOrderStatus(orderId);

    //Assert
    verify(orderClient).changeOrderStatus(any(), anyInt());
  }
}