package com.qa.business.service;

import javax.inject.Inject;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountRepo;
import com.qa.utils.JSONUtil;

public class AccountBusinessServiceImpl implements AccountBusinessService {

	@Inject
	private AccountRepo repo;

	@Override
	public String findAllAccount() {
		
		return repo.findAllAccount();
	}

	@Override
	public String findAnAccount(Integer accountNumberOfAccountToBeFound) {
		
		return repo.findAnAccount(accountNumberOfAccountToBeFound);
	}

	@Override
	public boolean updateAnAccount(String accountWithNewUpdatedDetails, Integer accountNumberOfAccountToBeUpdated) {
		
		return repo.updateAnAccount(JSONUtil.getObjectForJSON(accountWithNewUpdatedDetails, Account.class), accountNumberOfAccountToBeUpdated);
	}

	@Override
	public boolean createAccount(String accountToBeCreated) {
		Account accountObj = JSONUtil.getObjectForJSON(accountToBeCreated, Account.class);
		
		if(accountObj.getAccountNo() == 9999) {
			return false;
		}		
		return repo.createAccount(JSONUtil.getObjectForJSON(accountToBeCreated, Account.class));
	}

	@Override
	public boolean delete(Integer accountToBeDeleted) {
		return repo.delete(accountToBeDeleted);
	}
	
	
}
