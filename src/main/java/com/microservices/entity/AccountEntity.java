package com.microservices.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class AccountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer accountId;
	String name;
	BigDecimal balance;
	String phoneNumber;
	String aliasName;
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	List<PaymentEntity> payments;
}
