package br.com.postech.techchallengepayment.adapters.controller.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.postech.techchallengepayment.core.usecase.impl.ApprovePaymentUseCaseImpl;
import br.com.postech.techchallengepayment.core.usecase.impl.CreatePaymentUseCaseImpl;
import br.com.postech.techchallengepayment.util.PaymentTestProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerTest extends PaymentTestProvider {

  private static final String BASE_URL = "/techchallenge/payments";

  @MockBean
  private CreatePaymentUseCaseImpl createPaymentUseCase;

  @MockBean
  private ApprovePaymentUseCaseImpl approvePaymentUseCase;

  @Autowired
  private ObjectMapper mapper;

  @Autowired
  private MockMvc mockMvc;



  @Test
  void paymentCreationWithSuccess() throws Exception {
    //Arrange
    var paymentInput = getFakeInputPaymentRequest();
    var paymentOutput = getFakeOutputPayment();

    when(createPaymentUseCase.execute(any())).thenReturn(paymentOutput);

    //Act + Assert
    mockMvc.perform(
            post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(paymentInput)))
        .andExpect(status().isOk());
  }

  @Test
  void paymentApprovalWithSuccess() throws Exception {
    //Arrange
    var paymentInput = getFakeInputPaymentRequest();
    var paymentOutput = getFakeOutputPayment();

    when(approvePaymentUseCase.execute(any())).thenReturn(paymentOutput);

    //Act + Assert
    mockMvc.perform(
            patch(BASE_URL+ "/{paymentId}","4bf94b9a-699e-45ad-9d7e-46a3ba2c01c6")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(paymentInput)))
        .andExpect(status().isOk());
  }
}