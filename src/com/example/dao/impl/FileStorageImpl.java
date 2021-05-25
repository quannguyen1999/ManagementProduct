package com.example.dao.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.example.dao.FilesStorageDao;

public class FileStorageImpl implements FilesStorageDao{

	//đường dần file
	private static final String  UPLOAD_DIR = "images";

	//lấy file từ request
	private String  getFileName(Part part){
		final String  partHeader = part.getHeader("content-disposition");
		for(String content : part.getHeader("content-disposition").split(";")){
			if(content.trim().startsWith("filename")){
				return content.substring(content.indexOf('=')+1).trim().replace("\"", "" );
			}
		}
		return null;
	}

	//tiến hành save file trong máy
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
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public File getFolderUpload() {
		File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
		if (!folderUpload.exists()) {
			folderUpload.mkdirs();
		}
		return folderUpload;
	}
}
