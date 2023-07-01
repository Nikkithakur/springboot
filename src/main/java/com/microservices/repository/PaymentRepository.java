package com.microservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservices.entity.PaymentEntity;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {

	@Query(nativeQuery = true, value = "select * from payment_entity inner join account_entity_payments\r\n"
			+ " on payment_id = payments_payment_id\r\n"
			+ " where account_entity_account_id=:accountId")
	List<PaymentEntity> findByAccountId(Integer accountId);

}
