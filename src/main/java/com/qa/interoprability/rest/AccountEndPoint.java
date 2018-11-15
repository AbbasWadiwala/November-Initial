package com.qa.interoprability.rest;


import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.business.service.AccountBusinessService;

@Path("/account")
public class AccountEndPoint {
	
	@Inject
	private AccountBusinessService service;
	
	@Path("/json")
	@GET
	@Produces({ "application/json" })
	public String findAllAccounts() {
		return service.findAllAccount();
	}
	
	@Path("/json/{id}")
	@GET
	@Produces({ "application/json" })
	public String findAnAccount(@PathParam("id") Integer accountNumberOfAccountToBeFound) {
		return service.findAnAccount(accountNumberOfAccountToBeFound);
	}
	
	
	@Path("/json")
	@POST
	@Produces({ "application/json" })
	public boolean addAccount(String accountToBeAdded) {
		return service.createAccount(accountToBeAdded);
	}
	
	
	@Path("/json/{id}")
	@PUT
	@Produces({ "application/json"})
	public boolean updateAccount(@PathParam("id") Integer accountNumberOfAccountToBeUpdated, String accountWithNewInfo) {
		return service.updateAnAccount(accountWithNewInfo, accountNumberOfAccountToBeUpdated);
	}
	
	
	@Path("/json/{id}")
	@DELETE
	@Produces({ "application/json" })
	public boolean deleteAccount(@PathParam("id") Integer accountNumberOfAccountToBeDeleted) {
		return service.delete(accountNumberOfAccountToBeDeleted);
	}
	
	
}
