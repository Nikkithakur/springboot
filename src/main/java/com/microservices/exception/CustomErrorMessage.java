package com.microservices.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomErrorMessage {
	String errorMessage;
	String errorTimeStamp;
	String errorStatusCode;
}
