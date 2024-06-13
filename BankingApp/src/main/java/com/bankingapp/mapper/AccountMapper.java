package com.bankingapp.mapper;

import java.util.Optional;

import com.bankingapp.dto.AccountDto;
import com.bankingapp.entity.Account;

public class AccountMapper {
	public static Account mapToAccount(AccountDto accountdto) {
		Account account = new Account(accountdto.getId(),accountdto.getAccountHolderName(),accountdto.getBalance());
		return account;
	}
	
	public static AccountDto maptoAccountDto(Account savedAccount) {
		AccountDto accountdto = new AccountDto(savedAccount.getId(),savedAccount.getAccountHolderName(),savedAccount.getBalance());
		return accountdto;		
	}
}
