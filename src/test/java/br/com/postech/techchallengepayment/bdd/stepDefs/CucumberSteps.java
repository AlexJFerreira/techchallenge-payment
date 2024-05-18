package br.com.postech.techchallengepayment.bdd.stepDefs;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import br.com.postech.techchallengepayment.MongoDBTestContainerConfig;
import br.com.postech.techchallengepayment.adapters.controller.rest.request.PaymentRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.response.Response;
import java.math.BigDecimal;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
@ContextConfiguration(classes = MongoDBTestContainerConfig.class)
@Testcontainers
//@DataMongoTest
public class CucumberSteps {
  private Response response;
  private PaymentRequest request;

  @LocalServerPort
  private Integer port;

  @Container
  public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest").withExposedPorts(27020);

  private static final String HOST = "http://localhost:";
  private static final String BASE_URL_PREFIX = "/techchallenge/payments";

  @Given("client wants to create a payment")
  public void clientWantsToCreateAPayment() {
    request = new PaymentRequest("1", "36227297836", new BigDecimal("100.00"));
  }

  @When("client calls payment creation endpoint")
  public void clientCallsPaymentCreationEndpoint() {
    response = given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when()
        .post(HOST + port + BASE_URL_PREFIX);
  }

  @Then("client receives payment creation confirmation")
  public void clientReceivesPaymentCreationConfirmation() {
    response.then()
        .statusCode(HttpStatus.OK.value())
        .body(matchesJsonSchemaInClasspath("schemas/paymentCreationResponse.json"));;
  }
}
