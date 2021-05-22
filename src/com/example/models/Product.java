package com.example.models;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//@Data: tự tạo get và set
//@AllArgsConstructor: tự tạo constructor có tham số
//@NoArgsConstructor: tự tạo constructor không có tham số
//@Table và @Entity: để tạo bản
//@ToString: tự tạo to String
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Product implements Serializable{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int productId;
	
	@NotEmpty
	private String name;
	
	@NotNull
	private Float unitPrice;
	
	private int unitInStock;
	
	@NotEmpty
	private String description;
	
	@NotEmpty
	private String manufacturer;
	
	@NotEmpty
	private String category;
	
	private TypeConditionProduct typeConditionProduct;
	
	private String image;

	public Product(String name, Float unitPrice, int unitInStock, String description, String manufacturer,
			String category, TypeConditionProduct typeConditionProduct, String image) {
		super();
		this.name = name;
		this.unitPrice = unitPrice;
		this.unitInStock = unitInStock;
		this.description = description;
		this.manufacturer = manufacturer;
		this.category = category;
		this.typeConditionProduct = typeConditionProduct;
		this.image = image;
	}
	
	
}
