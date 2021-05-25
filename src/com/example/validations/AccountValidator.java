package com.example.validations;

import java.util.List;

import com.example.dao.AccountDao;
import com.example.dao.GenericDao;
import com.example.dao.impl.AccountImpl;
import com.example.dao.impl.GenericImpl;
import com.example.models.Account;
import com.example.models.CustomError;

//Để validator dữ liệu
public class AccountValidator {
	//khai báo các field
	private static String USERNAME = "userName";
	private static String PASSWORD = "password";

	//khai báo các mesage error
	private static String USERNAME_NOT_EXISTS = "username not exists";
	private static String PASSWORD_INCORRECT = "password not correct";

	private static GenericDao<Account> genericService = new GenericImpl(Account.class);
	private static AccountDao accountService = new AccountImpl();

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
