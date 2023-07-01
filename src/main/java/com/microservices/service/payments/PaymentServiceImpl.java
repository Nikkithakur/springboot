package com.microservices.service.payments;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.dto.PaymentDTO;
import com.microservices.entity.PaymentEntity;
import com.microservices.repository.PaymentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	private PaymentRepository paymentsRepo;

	@Override
	public List<PaymentDTO> fetchPaymentByAccountId(Integer accountId) {
		List<PaymentEntity> paymentEntities = paymentsRepo.findByAccountId(accountId);
		List<PaymentDTO> paymentsRespone = paymentEntities.stream().map(entity -> {
			PaymentDTO payment = new PaymentDTO();
			BeanUtils.copyProperties(entity, payment);
			return payment;
		}).collect(Collectors.toList());
		return paymentsRespone;
	}

}
