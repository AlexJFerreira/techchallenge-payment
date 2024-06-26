package br.com.postech.techchallengepayment.adapters.controller.rest.response;

import br.com.postech.techchallengepayment.core.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentApprovalResponse {

  private String id;
  private PaymentStatus status;
}
