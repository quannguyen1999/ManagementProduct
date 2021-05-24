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

public class AccountValidator {
	private static String USERNAME = "userName";

	private static String PASSWORD = "password";

	private static String TYPEACCOUNT = "typeAccount";

	private static String USERNAME_NOT_EXISTS = "username not exists";
	
	private static String PASSWORD_INCORRECT = "password not correct";

	private static GenericService<Account> genericService = new GenericDao<>(Account.class);

	private static AccountService accountService = new AccountDao();

	public static List<CustomError> validateCreate(Account account) {
		
		Account accountFind = null;
		
		List<CustomError> listCustomErrors = genericService.validator(account);

		if(listCustomErrors.size() >= 1) {
			return listCustomErrors;
		}
		
		accountFind = (accountService.findById(account.getUserName()));

		if(accountFind == null) {
			listCustomErrors.add(new CustomError(USERNAME, USERNAME_NOT_EXISTS));
			return listCustomErrors;
		}
		
		if(accountService.comparePassword(accountFind.getPassword(),account.getPassword()) == false) {
			listCustomErrors.add(new CustomError(PASSWORD, PASSWORD_INCORRECT));
		};

		return listCustomErrors;
	}
}
