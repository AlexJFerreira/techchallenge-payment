package br.com.postech.techchallengepayment.adapters.gateway.producer;

import br.com.postech.techchallengepayment.adapters.controller.rest.response.PaymentCreationResponse;
import br.com.postech.techchallengepayment.adapters.gateway.producer.request.PaymentApprovalMessage;
import br.com.postech.techchallengepayment.core.domain.entity.Payment;
import br.com.postech.techchallengepayment.core.domain.enums.PaymentStatus;
import br.com.postech.techchallengepayment.core.gateway.producer.PaymentProducerGateway;
import br.com.postech.techchallengepayment.infra.EventsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentProducerGatewayImpl implements PaymentProducerGateway {
  private final EventsConfig config;
  private final ModelMapper modelMapper;
  private final QueueMessagingTemplate messagingTemplate;

  @Override
  public void notifyPaymentCreationQueue(Payment payment) {
    PaymentCreationResponse paymentCreationResponse = modelMapper.map(payment, PaymentCreationResponse.class);
    log.info("Notifying queue {}", config.getPaymentsCreationQueue());
    messagingTemplate.convertAndSend(config.getPaymentsCreationQueue(), paymentCreationResponse);
  }

  @Override
  public void notifyPaymentApprovalQueue(String paymentId, PaymentStatus paymentStatus) {
    PaymentApprovalMessage paymentApprovalMessage = new PaymentApprovalMessage(paymentId, paymentStatus);
    log.info("Notifying queue {}", config.getPaymentsApprovalQueue());
    messagingTemplate.convertAndSend(config.getPaymentsApprovalQueue(), paymentApprovalMessage);
  }
}
