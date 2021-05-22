package com.example.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Customer implements Serializable{
	@Id
	private String customerID;
	private String address;
	private String city;
	private String email;
	private String firstName;
	private String lastName;
	private String phone;
	
	@OneToOne
	@JoinColumn(name = "account")
	private Account account;
}
