package com.example.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class OrderDetail implements Serializable{
	@EmbeddedId
	private OrderProductPK id;
	private int quantity;
	private float totalAmount;
	
	@ManyToOne
	@MapsId("orderId")
	@JoinColumn(name = "orderId")
	private OrderProduct orderId;
	
	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "productId")
	private Product productId;
}
