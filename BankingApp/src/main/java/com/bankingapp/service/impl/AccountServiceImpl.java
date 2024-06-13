package com.bankingapp.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.RuntimeBeanNameReference;
import org.springframework.stereotype.Service;

import com.bankingapp.dto.AccountDto;
import com.bankingapp.entity.Account;
import com.bankingapp.mapper.AccountMapper;
import com.bankingapp.repository.AccountRepository;
import com.bankingapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

//	@Autowired - constructor injection is better for code clarity & preventing run time errors
	private AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.maptoAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));
		return AccountMapper.maptoAccountDto(account);
	}

	@Override
	public AccountDto modifyBalance(Long id, double addAmount) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
		double newBalance = addAmount + account.getBalance();
		account.setBalance(newBalance);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.maptoAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double withdrawalAmount) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
		if (withdrawalAmount > account.getBalance()) {
			throw new RuntimeException("Account has insufficient balance");
		}
		double newBalance = account.getBalance() - withdrawalAmount;
		if (newBalance < 0) {
			throw new RuntimeException("Account has insufficient balance");
		}
		account.setBalance(newBalance);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.maptoAccountDto(savedAccount);
	}

	@Override
	public String delete(Long id) {
		// TODO Auto-generated method stub
		
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
		
		accountRepository.delete(account);
		return "Account deleted";
	}

	
	public List<AccountDto> getAll() {
		List<Account> account =  accountRepository.findAll();
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		
		List<AccountDto> accountDto = new ArrayList<AccountDto>();
		for (int i = 0; i < account.size();i++) {
			accountDto.add(AccountMapper.maptoAccountDto(account.get(i)));
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" + accountDto.get(i));
		}
		return accountDto;
		
	}
	

}
