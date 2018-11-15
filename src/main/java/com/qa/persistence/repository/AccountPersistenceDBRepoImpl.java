package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;


import javax.enterprise.inject.Default;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Account;
import com.qa.utils.JSONUtil;

@RequestScoped
@Default
@Transactional(SUPPORTS)
public class AccountPersistenceDBRepoImpl implements AccountRepo {
	
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	public String findAllAccount() {
		TypedQuery<Account> query = manager.createQuery("SELECT m FROM Account m ORDER BY m.accountNo DESC", Account.class);
		return JSONUtil.getJSONForObject(query.getResultList());
	}

	public String findAnAccount(Integer accountNumberOfAccountToBeFound) {		
		return JSONUtil.getJSONForObject(manager.find(Account.class, accountNumberOfAccountToBeFound));
	}

	@Transactional(REQUIRED)
	public boolean updateAnAccount(Account accountWithNewUpdatedDetails, Integer accountNumberOfAccountToBeUpdated) {
		Account accountToBeUpdated = manager.find(Account.class, accountNumberOfAccountToBeUpdated);
		accountToBeUpdated.setFirstname(accountWithNewUpdatedDetails.getFirstname());
		accountToBeUpdated.setSurname(accountWithNewUpdatedDetails.getSurname());
		manager.merge(accountToBeUpdated);
		return manager.find(Account.class, accountToBeUpdated.getAccountNo()) != null ? true : false;
	}

	@Transactional(REQUIRED)
	public boolean createAccount(Account accountToBeCreated) {
		manager.persist(accountToBeCreated);
		return manager.find(Account.class, accountToBeCreated.getAccountNo()) != null ? true : false;
	}

	@Transactional(REQUIRED)
	public boolean delete(Integer accountNumberOfAccountToBeDeleted) {
		Account accountToBeDeleted = manager.find(Account.class, accountNumberOfAccountToBeDeleted);
		manager.remove(accountToBeDeleted);
		return manager.find(Account.class, accountNumberOfAccountToBeDeleted) == null ? true : false;
	}


}
