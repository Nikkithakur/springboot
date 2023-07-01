package com.microservices.service.accounts;

import java.util.List;

import com.microservices.dto.AccountDTO;
import com.microservices.exception.AccountNotPresentException;

public interface IAccountService {

	List<AccountDTO> fetchAccounts(Integer limit);

	AccountDTO fetchAccountById(Integer id) throws AccountNotPresentException;

	AccountDTO createAccount(AccountDTO accountRequest);

	void deleteAccountById(Integer id);

}
