package com.qa.persistence.repository;


import java.util.HashMap;


import javax.enterprise.inject.Alternative;
import javax.faces.bean.ApplicationScoped;

import com.qa.persistence.domain.Account;
import com.qa.utils.JSONUtil;

@ApplicationScoped
@Alternative
public class AccountPersistenceMapRepoImpl implements AccountRepo {
	
	private HashMap<Integer, Account> accountList = new HashMap<Integer, Account>();
	
	@Override
	public String findAllAccount() {		
		return JSONUtil.getJSONForObject(accountList);
	}

	@Override
	public String findAnAccount(Integer accountNumberOfAccountToBeFound) {
		for (Integer i : accountList.keySet()) {
			if (i == accountNumberOfAccountToBeFound) {
				return JSONUtil.getJSONForObject(accountList.get(i));
			}
		}		
		return "Account Not Found";
	}

	@Override
	public boolean updateAnAccount(Account accountWithNewUpdatedDetails, Integer accountNumberOld) {
		Account accountToBeUpdated = this.accountList.get(accountNumberOld);
		accountToBeUpdated.setFirstname(accountWithNewUpdatedDetails.getFirstname());
		accountToBeUpdated.setSurname(accountWithNewUpdatedDetails.getSurname());		
		return this.accountList.containsValue(accountWithNewUpdatedDetails);
	}

	@Override
	public boolean createAccount(Account accountToBeCreated) {
		this.accountList.put(accountToBeCreated.getAccountNo(), accountToBeCreated);
		return this.accountList.containsValue(accountToBeCreated);
	}

	@Override
	public boolean delete(Integer accountNumberToBeDeleted) {
		this.accountList.remove(accountNumberToBeDeleted);
		return this.accountList.containsKey(accountNumberToBeDeleted);
	}

	

}
