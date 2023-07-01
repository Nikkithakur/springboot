package com.microservices.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer paymentId;
	String message;

}
