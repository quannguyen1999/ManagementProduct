package com.example.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Product implements Serializable{
	@Id
	private String productId;
	private Float unitPrice;
	private int unitInStock;
	private String description;
	private String manufacturer;
	private String category;
	private TypeConditionProduct typeConditionProduct;
}
