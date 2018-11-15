package com.qa.business.service;


public interface AccountBusinessService {
	
	String findAllAccount();

	String findAnAccount(Integer accountNumber);

	boolean updateAnAccount(String accountNew, Integer accountNumberOld);

	boolean createAccount(String account);

	boolean delete(String account);
	
}
