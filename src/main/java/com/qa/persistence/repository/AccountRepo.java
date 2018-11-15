package com.qa.persistence.repository;

import com.qa.persistence.domain.Account;

public interface AccountRepo {
	
	String findAllAccount();

	String findAnAccount(Integer accountNumberOfAccountToBeFound);

	boolean updateAnAccount(Account accountWithNewUpdatedDetails, Integer accountNumberOfAccountToBeUpdated);

	boolean createAccount(Account accountToBeCreated);

	boolean delete(Integer accountNumberOfAccountToBeDeleted);
}
