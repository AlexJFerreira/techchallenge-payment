package br.com.postech.techchallengepayment.util;

import br.com.postech.techchallengepayment.adapters.gateway.client.request.OrderStatus;
import br.com.postech.techchallengepayment.adapters.gateway.client.response.OrderResponse;
import br.com.postech.techchallengepayment.adapters.gateway.database.entity.PaymentEntity;
import br.com.postech.techchallengepayment.core.domain.entity.Payment;
import br.com.postech.techchallengepayment.core.domain.enums.PaymentStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class PaymentTestProvider {

  public OrderResponse getFakeOrderResponse() {
    OrderResponse orderResponse = new OrderResponse();
    orderResponse.setId(1);
    orderResponse.setOrderDate(LocalDateTime.now());
    orderResponse.setStatus(OrderStatus.PREPARING);
    orderResponse.setLastUpdateDate(LocalDateTime.now());
    return orderResponse;
  }

  public PaymentEntity getFakeInputPaymentEntity() {
    PaymentEntity payment = new PaymentEntity();
    payment.setCpf("36227297836");
    payment.setStatus(PaymentStatus.APPROVED);
    payment.setAmount(new BigDecimal("100.00"));
    return payment;
  }

  public PaymentEntity getFakeOutputPaymentEntity() {
    PaymentEntity payment = new PaymentEntity();
    payment.setId("7203e682-a02c-4f6e-b555-c39f892c9f0c");
    payment.setCpf("36227297836");
    payment.setStatus(PaymentStatus.APPROVED);
    payment.setAmount(new BigDecimal("100.00"));
    return payment;
  }

  public Payment getFakeInputPayment() {
    Payment payment = new Payment();
    payment.setCpf("36227297836");
    payment.setStatus(PaymentStatus.APPROVED);
    payment.setAmount(new BigDecimal("100.00"));
    return payment;
  }

  public Payment getFakeOutputPayment() {
    Payment payment = new Payment();
    payment.setId("7203e682-a02c-4f6e-b555-c39f892c9f0c");
    payment.setCpf("36227297836");
    payment.setStatus(PaymentStatus.APPROVED);
    payment.setAmount(new BigDecimal("100.00"));
    payment.setOrderId("1");
    return payment;
  }


}