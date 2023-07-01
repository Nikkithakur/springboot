package com.microservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.dto.PaymentDTO;
import com.microservices.service.payments.IPaymentService;

import jakarta.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/payments")
public class PaymentsController {
	
	@Autowired
	private IPaymentService paymentServiceImpl;

	@GetMapping("/fetchPaymentsById/{accountId}")
	public ResponseEntity<List<PaymentDTO>> fetchPayments(
			@PathVariable("accountId") @NotEmpty(message = "AccountId cannot be empty") Integer accountId) {
		List<PaymentDTO> payments = paymentServiceImpl.fetchPaymentByAccountId(accountId);
		return new ResponseEntity<List<PaymentDTO>>(payments, HttpStatus.OK);
	}
}
