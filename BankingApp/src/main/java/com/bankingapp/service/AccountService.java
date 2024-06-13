package com.bankingapp.service;

import com.bankingapp.dto.AccountDto;

public interface AccountService {
	AccountDto createAccount(AccountDto account);
	AccountDto getAccountById(Long id);
	AccountDto modifyBalance(Long id, double addAmount);
	AccountDto withdraw(Long id, double withdrawalAmount);
	String delete(Long id);

}
