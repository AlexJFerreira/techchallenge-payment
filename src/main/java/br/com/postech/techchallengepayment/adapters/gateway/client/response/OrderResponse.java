package br.com.postech.techchallengepayment.adapters.gateway.client.response;

import br.com.postech.techchallengepayment.adapters.gateway.client.request.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OrderResponse {
  private Integer id;
  private OrderStatus status;
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime orderDate;
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime lastUpdateDate;
}
