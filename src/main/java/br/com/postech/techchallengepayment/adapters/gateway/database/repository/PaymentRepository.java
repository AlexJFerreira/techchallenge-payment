package br.com.postech.techchallengepayment.adapters.gateway.database.repository;


import br.com.postech.techchallengepayment.adapters.gateway.database.entity.PaymentEntity;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<PaymentEntity, String> {
  Optional<PaymentEntity> findByOrderId(Integer orderId);
}
