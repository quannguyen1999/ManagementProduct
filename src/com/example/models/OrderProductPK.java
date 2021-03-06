package com.example.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "orderId")
	private String orderId;
	
	@Column(name = "productID")
	private int productId;	
}
