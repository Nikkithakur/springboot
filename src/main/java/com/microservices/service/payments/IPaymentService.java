package com.microservices.service.payments;

import java.util.List;
import java.util.Optional;

import com.microservices.dto.PaymentDTO;

public interface IPaymentService {

	List<PaymentDTO> fetchPaymentByAccountId(Integer accountId);

}
