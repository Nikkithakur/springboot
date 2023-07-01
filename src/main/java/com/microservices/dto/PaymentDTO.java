package com.microservices.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PaymentDTO {
	Integer paymentId;
	@NotBlank
	String message;
}
