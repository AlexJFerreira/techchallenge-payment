package br.com.postech.techchallengepayment.adapters.gateway.database.repository;


import br.com.postech.techchallengepayment.adapters.gateway.database.entity.PaymentEntity;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {
  Optional<PaymentEntity> findByOrderId(Integer orderId);
}
