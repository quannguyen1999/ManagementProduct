package com.example.services;

import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public interface FilesStorageService {

	public String save(HttpServletRequest request);
	
}
