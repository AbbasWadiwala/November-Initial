package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
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
		TypedQuery<Account> query = manager.createQuery("SELECT m FROM Account m ORDER BY m.accountNumber DESC",
				Account.class);
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
		return manager.contains(accountWithNewUpdatedDetails);
	}

	@Transactional(REQUIRED)
	public boolean createAccount(Account accountToBeCreated) {
		manager.persist(accountToBeCreated);
		return manager.contains(accountToBeCreated);
	}

	@Transactional(REQUIRED)
	public boolean delete(Account accountToBeDeleted) {
		manager.remove(accountToBeDeleted);
		return manager.contains(accountToBeDeleted);
	}

	// public Account findAnAccountJPQL(Integer accountNumber) {
	// 		TypedQuery<Account> query = manager.createQuery("SELECT m FROM Account m
	// 		where m.accountNumber=" + accountNumber, Account.class);
	// 		return query.getSingleResult();
	// }
	//

	// @Transactional(REQUIRED)
	// public String createAccountJTAGPQL(String account) {
	// 		Account anAccount = JSONUtil.getObjectForJSON(account, Account.class);
	// 		manager.persist(anAccount);
	// 		return "{\"message\": \"account sucessfully added\"}";
	// }

}
