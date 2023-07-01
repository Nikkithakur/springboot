package com.microservices.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AccountDTO {
	Integer accountId;
	@NotBlank
	String name;
	@Positive
	BigDecimal balance;
	@NotBlank
	String phoneNumber;
	String aliasName;
	List<PaymentDTO> payments = new ArrayList<>();
//	@JsonIgnore
//	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
//	@JsonAnyGetter
//	public Map<String, Object> getAdditionalProperties() {
//	return this.additionalProperties;
//	}
//
//	@JsonAnySetter
//	public void setAdditionalProperty(String name, Object value) {
//	this.additionalProperties.put(name, value);
//	}
}
