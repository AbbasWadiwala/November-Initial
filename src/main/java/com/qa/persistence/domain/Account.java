package com.qa.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer accountNo;
	@Column(length = 20)
	private String firstname;
	@Column(length = 20)
	private String surname;
	
	public Integer getAccountNo() {
		return accountNo;
	}
	
	public Account setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
		return this;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public Account setFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public Account setSurname(String surname) {
		this.surname = surname;
		return this;
	}

}
