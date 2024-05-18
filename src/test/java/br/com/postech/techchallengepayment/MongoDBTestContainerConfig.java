package br.com.postech.techchallengepayment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.testcontainers.containers.MongoDBContainer;

@Configuration
@EnableMongoRepositories
public class MongoDBTestContainerConfig {

  @Bean
  public MongoDBContainer mongoDBContainer() {
    MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest").withExposedPorts(27020);
    mongoDBContainer.start();
    return mongoDBContainer;
  }

  @Bean
  public MongoTemplate mongoTemplate(MongoDBContainer mongoDBContainer) {
    return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoDBContainer.getReplicaSetUrl()));
  }
}
