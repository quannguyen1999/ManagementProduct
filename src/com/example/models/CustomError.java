package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
//@Data: tự tạo get và set
//@AllArgsConstructor: tự tạo constructor có tham số
//Class này để customer error
//field: tên field
//messager: nội dung lỗi
@Data
@AllArgsConstructor
public class CustomError {
	private String field;
	private String message;
}