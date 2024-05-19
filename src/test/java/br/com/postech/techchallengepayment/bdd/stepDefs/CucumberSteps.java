package br.com.postech.techchallengepayment.bdd.stepDefs;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import br.com.postech.techchallengepayment.adapters.controller.rest.request.PaymentRequest;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.response.Response;
import java.math.BigDecimal;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
public class CucumberSteps {
  private Response response;
  private PaymentRequest request;



  private static final String HOST = "http://localhost:8082";
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
        .post(HOST + BASE_URL_PREFIX);
  }

  @Then("client receives payment creation confirmation")
  public void clientReceivesPaymentCreationConfirmation() {
    response.then()
        .statusCode(HttpStatus.OK.value())
        .body(matchesJsonSchemaInClasspath("schemas/paymentCreationResponse.json"));;
  }
}
