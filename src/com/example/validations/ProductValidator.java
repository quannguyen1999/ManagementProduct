package com.example.validations;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.example.dao.GenericDao;
import com.example.dao.impl.GenericImpl;
import com.example.models.Account;
import com.example.models.CustomError;
import com.example.models.Product;

//Để validator dữ liệu
public class ProductValidator {
	//khai báo các field
	private static String IMAGE = "image";
	
	//khai báo các mesage error
	private static String IMAGE_IS_REQUIRED = "image is required";

	private static GenericDao<Product> genericService = new GenericImpl<>(Product.class);

	public static List<CustomError> validateInsertProduct(Product product, HttpServletRequest request) {
		List<CustomError> listCustomErrors = genericService.validator(product);

		if(listCustomErrors.size() >= 1) {
			return listCustomErrors;
		}
		
		try {
			if(request.getPart("image").getSize() <= 0 ) {
				listCustomErrors.add(new CustomError(IMAGE, IMAGE_IS_REQUIRED));
			}
		} catch (IOException e) {
			listCustomErrors.add(new CustomError(IMAGE, IMAGE_IS_REQUIRED));
			e.printStackTrace();
		} catch (ServletException e) {
			listCustomErrors.add(new CustomError(IMAGE, IMAGE_IS_REQUIRED));
			e.printStackTrace();
		}
		return listCustomErrors;
	}
}
