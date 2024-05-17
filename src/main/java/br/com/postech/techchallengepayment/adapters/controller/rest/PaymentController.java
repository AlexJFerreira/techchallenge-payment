package br.com.postech.techchallengepayment.adapters.controller.rest;


import br.com.postech.techchallengepayment.adapters.controller.rest.request.PaymentRequest;
import br.com.postech.techchallengepayment.adapters.controller.rest.response.PaymentApprovalResponse;
import br.com.postech.techchallengepayment.adapters.controller.rest.response.PaymentCreationResponse;
import br.com.postech.techchallengepayment.core.domain.entity.Payment;
import br.com.postech.techchallengepayment.core.usecase.ApprovePaymentUseCase;
import br.com.postech.techchallengepayment.core.usecase.CreatePaymentUseCase;
import br.com.postech.techchallengepayment.core.usecase.SearchPaymentByOrderIdUseCase;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/techchallenge/payments")
public class PaymentController {

  private final ModelMapper modelMapper;
  private final SearchPaymentByOrderIdUseCase searchPaymentByOrderIdUseCase;
  private final ApprovePaymentUseCase approvePaymentUseCase;
  private final CreatePaymentUseCase createPaymentUseCase;

  @GetMapping("/orders/{orderId}")
  @ResponseStatus(HttpStatus.OK)
  public PaymentApprovalResponse getPaymentByOrderId(@NotNull @PathVariable final Integer orderId) {
    log.info("Payment search by orderId {} ", orderId);
    var payment = searchPaymentByOrderIdUseCase.execute(orderId);
    return modelMapper.map(payment, PaymentApprovalResponse.class);
  }

  @PatchMapping("/{paymentId}")
  @ResponseStatus(HttpStatus.OK)
  public PaymentApprovalResponse paymentApproval(@NotNull @PathVariable final String paymentId) {
    log.info("Payment approval for paymentId: {} received", paymentId);
    return modelMapper.map(approvePaymentUseCase.execute(paymentId), PaymentApprovalResponse.class);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  @Transactional
  public PaymentCreationResponse paymentCreation(@NotNull @RequestBody final PaymentRequest paymentRequest) {
    log.info("Payment request: {} received", paymentRequest);
    var payment = modelMapper.map(paymentRequest, Payment.class);
    return modelMapper.map(createPaymentUseCase.execute(payment), PaymentCreationResponse.class);
  }
}
