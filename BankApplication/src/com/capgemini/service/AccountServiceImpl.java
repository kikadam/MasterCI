package com.capgemini.service;

import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
	
	AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository)
	{
		this.accountRepository=accountRepository;
	}
	@Override
	public Account createAccount(int accountNumber,int amount)throws InsufficientInitialBalanceException
	{
		if(amount<500)
		{
			throw new InsufficientInitialBalanceException();
		}
		
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		
		if(accountRepository.save(account))
		{
			return account;
		}
		
		return null;
		
	}
	@Override
	public int withdrawAmmount(int accountNumber, int amount) throws InvalidAccountNumberException, InsufficientInitialBalanceException {
		
		Account a = new Account();
		accountRepository.searchAccount(accountNumber);
		
		if(accountNumber != a.getAccountNumber())
		{
			throw new InvalidAccountNumberException();
			
		}
		
		
		
		
		if ( amount < a.getBal())
		{
			throw new InsufficientInitialBalanceException();
		}
			
		
				
		return a.getBal();
	}

}
