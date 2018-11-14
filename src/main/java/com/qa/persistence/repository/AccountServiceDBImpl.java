package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Account;
import com.qa.utils.JSONUtil;

@Transactional(SUPPORTS)
public class AccountServiceDBImpl {
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	
	public List<Account> findAllAccountJPQL() {
        TypedQuery<Account> query = manager.createQuery("SELECT m FROM Account m ORDER BY m.accountNumber DESC", Account.class);
        return query.getResultList();
    }
	
	public Account findAnAccountJPQL(Integer accountNumber) {
        TypedQuery<Account> query = manager.createQuery("SELECT m FROM Account m where m.accountNumber=" + accountNumber, Account.class);
        return query.getSingleResult();
    }	
	
	public Account FindAnAccount(Integer accountNumber) {
        return manager.find(Account.class, accountNumber);
    }
	
	@Transactional(REQUIRED)
	public boolean updateAnAccountJTA(Account accountNew, Integer accountNumberOld) {
		Account accountOld = manager.find(Account.class, accountNumberOld);		
		accountOld.setFirstname(accountNew.getFirstname());
		accountOld.setSurname(accountNew.getSurname());
		manager.merge(accountOld);		  
		return manager.contains(accountNew);  
    }
	
	@Transactional(REQUIRED)
	public String createAccountJTA(String account) {
		Account anAccount = JSONUtil.getObjectForJSON(account, Account.class);
		manager.persist(anAccount);
		return "{\"message\": \"account sucessfully added\"}";
	}
	
	@Transactional(REQUIRED)
    public boolean createAccountJTA(Account account) {
		manager.persist(account);
		return manager.contains(account);      
    }
	
	@Transactional(REQUIRED)
    public boolean deleteJTA(Account account) {
		manager.remove(account);        
        return manager.contains(account);
    }
	


}
