package com.bankingapp.service;

import java.util.List;

import com.bankingapp.dto.AccountDto;

public interface AccountService {
	AccountDto createAccount(AccountDto account);
	AccountDto getAccountById(Long id);
	AccountDto modifyBalance(Long id, double addAmount);
	AccountDto withdraw(Long id, double withdrawalAmount);
	String delete(Long id);
	List<AccountDto> getAll();
}
