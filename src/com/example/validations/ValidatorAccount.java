package com.example.validations;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.example.models.Account;
import com.example.models.CustomError;
import com.example.models.TypeAccount;
import com.example.services.AccountService;
import com.example.services.GenericService;
import com.example.services.impl.AccountDao;
import com.example.services.impl.GenericDao;

public class ValidatorAccount {
	private static String USERNAME = "username";;

	private static String PASSWORD = "password";

	private static String TYPEACCOUNT = "typeAccount";

	private static String USERNAME_EXISTS = "username had exists";

	private GenericService<Account> genericService = new GenericDao<>(Account.class);

	private AccountService accountService = new AccountDao();

	public List<CustomError> validateCreate(Account account) {
		List<CustomError> listCustomErrors = genericService.validator(account);

		if(listCustomErrors.size() >= 1) {
			return listCustomErrors;
		}

		if(accountService.findById(account.getUserName()) != null) {
			listCustomErrors.add(new CustomError(USERNAME, USERNAME_EXISTS));
		}

		return null;
	}
}
