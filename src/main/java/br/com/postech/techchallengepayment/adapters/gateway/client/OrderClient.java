package br.com.postech.techchallengepayment.adapters.gateway.client;

import br.com.postech.techchallengepayment.adapters.gateway.client.request.OrderRequest;
import br.com.postech.techchallengepayment.adapters.gateway.client.response.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "order", url = "http://localhost:5000")
public interface OrderClient {

  @PatchMapping(value = "/techchallenge/orders/{orderId}")
  OrderResponse changeOrderStatus(@RequestBody OrderRequest orderRequest, @PathVariable final Integer orderId);
}
