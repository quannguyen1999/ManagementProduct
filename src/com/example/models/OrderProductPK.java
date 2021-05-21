package com.example.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Embeddable
public class OrderProductPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "orderId")
	private String orderId;
	
	@Column(name = "productID")
	private String productId;	
}
