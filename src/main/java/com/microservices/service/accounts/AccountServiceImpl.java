package com.microservices.service.accounts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.microservices.dto.AccountDTO;
import com.microservices.dto.PaymentDTO;
import com.microservices.entity.AccountEntity;
import com.microservices.entity.PaymentEntity;
import com.microservices.exception.AccountNotPresentException;
import com.microservices.repository.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private AccountRepository accountRepo;

	@Override
	public List<AccountDTO> fetchAccounts(Integer limit) {
		log.info("fetching accountlist and limit: {} ", limit);
		List<AccountDTO> accountList = accountRepo.findAll().stream().map(accountEntity -> {
			AccountDTO accountDto = copyProperties(accountEntity, new AccountDTO());
			List<PaymentDTO> paymentDtoList = new ArrayList<>();
			if (!CollectionUtils.isEmpty(accountEntity.getPayments()))
				accountEntity.getPayments().forEach(paymentEntity -> {
					paymentDtoList.add(copyProperties(paymentEntity, new PaymentDTO()));
				});
			accountDto.setPayments(paymentDtoList);
			return accountDto;
		}).collect(Collectors.toList());
		return limit != null && limit > 0 && limit <= accountList.size() ? accountList.subList(0, limit) : accountList;
	}

	@Override
	public AccountDTO fetchAccountById(Integer id) throws AccountNotPresentException {
		Optional<AccountEntity> optAccountEntity = accountRepo.findById(id);
		if (optAccountEntity.isEmpty()) {
			log.error("Account not found by id:{}", id);
			throw new AccountNotPresentException(String.format("Account not found by id:%s", id));
		}
		AccountDTO accountDto = copyProperties(optAccountEntity.get(), new AccountDTO());
		List<PaymentDTO> paymentDtoList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(optAccountEntity.get().getPayments())) {
			optAccountEntity.get().getPayments().forEach(paymentEntity -> {
				paymentDtoList.add(copyProperties(paymentEntity, new PaymentDTO()));
			});
		}
		accountDto.setPayments(paymentDtoList);
		return accountDto;
	}

	@Override
	public AccountDTO createAccount(AccountDTO accountRequest) {
		// save in DB
		AccountEntity accountEntity = copyProperties(accountRequest, new AccountEntity());
		List<PaymentEntity> paymentEntities = new ArrayList<>();
		if (!CollectionUtils.isEmpty(accountRequest.getPayments())) {
			accountRequest.getPayments().forEach(paymentDto -> {
				PaymentEntity paymentEntity = copyProperties(paymentDto, new PaymentEntity());
				paymentEntities.add(paymentEntity);
			});
			accountEntity.setPayments(paymentEntities);
		}
		AccountEntity savedAccountEntity = accountRepo.save(accountEntity);

		// create response out of saved data
		AccountDTO accountDto = copyProperties(savedAccountEntity, new AccountDTO());
		List<PaymentDTO> paymentDtoList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(savedAccountEntity.getPayments())) {
			savedAccountEntity.getPayments().forEach(paymentEntity -> {
				paymentDtoList.add(copyProperties(paymentEntity, new PaymentDTO()));
			});
		}
		accountDto.setPayments(paymentDtoList);
		return accountDto;
	}

	@Override
	public void deleteAccountById(Integer id) {
		accountRepo.deleteById(id);
	}

	private <S, D> D copyProperties(S sourceObj, D destObj) {
		BeanUtils.copyProperties(sourceObj, destObj);
		return destObj;
	}

}
