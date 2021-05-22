package com.example.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@ToString
public class Account implements Serializable{
	@Id
	@NotEmpty(message = "userName can't be null")
	private String userName;
	
	@Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$^&+=])(?=\\S+$).{8,}$",message = "password must have a-z and A-Z and [!@#$%^&*()] and [0-9]")
	private String password;
	
	private TypeAccount typeAccount;

	public Account(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
}
