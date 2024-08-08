package br.com.postech.techchallengepayment.adapters.consumer;

import br.com.postech.techchallengepayment.adapters.controller.rest.request.PaymentRequest;
import br.com.postech.techchallengepayment.core.domain.entity.Payment;
import br.com.postech.techchallengepayment.core.usecase.CreatePaymentUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrdersConsumer {

  private final CreatePaymentUseCase createPaymentUseCase;
  private final ObjectMapper objectMapper;
  private final ModelMapper modelMapper;


  @SqsListener(value = "${events.orders-queue}", deletionPolicy = SqsMessageDeletionPolicy.NO_REDRIVE)
  public void consume(Message<String> message) throws JsonProcessingException {
    log.info("Order created: {} received", message.getPayload());
    PaymentRequest paymentRequest = objectMapper.readValue(StringEscapeUtils.unescapeJson(message.getPayload()), PaymentRequest.class);
    Payment payment = modelMapper.map(paymentRequest, Payment.class);
    createPaymentUseCase.execute(payment);
  }

}
