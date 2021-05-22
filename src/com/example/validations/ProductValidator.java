package com.example.validations;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.example.models.Account;
import com.example.models.CustomError;
import com.example.models.Product;
import com.example.services.GenericService;
import com.example.services.impl.GenericDao;

public class ProductValidator {
	private static String IMAGE = "image";
	
	private static String IMAGE_IS_REQUIRED = "image is required";

	private static GenericService<Product> genericService = new GenericDao<>(Product.class);

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			listCustomErrors.add(new CustomError(IMAGE, IMAGE_IS_REQUIRED));
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCustomErrors;
	}
}
