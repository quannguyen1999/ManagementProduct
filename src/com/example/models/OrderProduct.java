package com.example.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
public class OrderProduct implements Serializable{
	@Id
	private String orderId;
	
	@ManyToOne
	@JoinColumn(name = "customer")
	private Customer customer;
	
	private Date orderDate;
	
	private float totalPrice;
	
}
