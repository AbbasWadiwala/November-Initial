package com.qa.business.service;


public interface AccountBusinessService {
	
	String findAllAccount();

	String findAnAccount(Integer accountNumberOfAccountToBeFound);

	boolean updateAnAccount(String accountHoldingNewInfo, Integer accountNumberOfAccountToBeUpdated);

	boolean createAccount(String accountToBeCreated);

	boolean delete(Integer accountNumberOfAccountToBeDeleted);
	
}
