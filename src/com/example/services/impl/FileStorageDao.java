package com.example.services.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.example.services.FilesStorageService;

public class FileStorageDao implements FilesStorageService{

	private static final String  UPLOAD_DIR = "images";

	private String  getFileName(Part part){
		final String  partHeader = part.getHeader("content-disposition");
		System.out.println("*****partHeader :"+ partHeader);
		for(String content : part.getHeader("content-disposition").split(";")){
			if(content.trim().startsWith("filename")){
				return content.substring(content.indexOf('=')+1).trim().replace("\"", "" );
			}
		}
		return null;
	}

	@Override
	public String save(HttpServletRequest request) {
		String fileName="";
		try {
			for (Part filePart : request.getParts()) {
				fileName = (String) getFileName(filePart);
				String applicationPath = request.getServletContext().getRealPath("");
				String basePath = applicationPath + File.separator + UPLOAD_DIR+File.separator;
				InputStream inputStream = null;
				OutputStream outputStream = null;
				try {
					File outputFilePath = new  File(basePath + fileName);
					inputStream = filePart.getInputStream();
					outputStream = new FileOutputStream(outputFilePath);
					int read = 0;
					final byte[] bytes =  new  byte[1024];
					while((read = inputStream.read(bytes)) != -1){
						outputStream.write(bytes, 0, read);
					}
				} catch (Exception e) {
					e.printStackTrace();
					fileName = "";
				}finally{
					if(inputStream != null){
						inputStream.close();
					}
					if(outputStream != null){
						outputStream.close();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileName;
	}

	/**
	 * Extracts file name from HTTP header content-disposition
	 */
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
	public File getFolderUpload() {
		File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
		if (!folderUpload.exists()) {
			folderUpload.mkdirs();
		}
		return folderUpload;
	}
}
