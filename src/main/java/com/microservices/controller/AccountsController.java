package com.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.dto.AccountDTO;
import com.microservices.exception.AccountNotPresentException;
import com.microservices.service.accounts.IAccountService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountsController {

	@Autowired
	private IAccountService accountServiceImpl;

	@GetMapping(value = { "/fetchAccounts", "/fetchAccounts/{limit}" })
	public ResponseEntity<List<AccountDTO>> fetchAccounts(
			@PathVariable(name = "limit", required = false) Integer limit) {
		List<AccountDTO> accountList = accountServiceImpl.fetchAccounts(limit);
		return new ResponseEntity<List<AccountDTO>>(accountList, HttpStatus.OK);
	}

	@GetMapping("/fetchAccount/{id}")
	public ResponseEntity<AccountDTO> fetchAccountsById(@PathVariable("id") Integer id)
			throws AccountNotPresentException {
		AccountDTO account = accountServiceImpl.fetchAccountById(id);
		return new ResponseEntity<AccountDTO>(account, HttpStatus.OK);
	}

	@PostMapping("/createAccount")
	public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountDTO accountRequest) {
		log.info("incoming request: {}", accountRequest);
		AccountDTO account = accountServiceImpl.createAccount(accountRequest);
		return new ResponseEntity<AccountDTO>(account, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteAccount/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable("id") Integer id) {
		accountServiceImpl.deleteAccountById(id);
		return new ResponseEntity<String>("Account deletion successfull", HttpStatus.ACCEPTED);

	}
}
