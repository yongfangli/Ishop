package com.thinkgem.jeesite.modules.postManeger;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.gridfs.GridFsOperations;

import com.mongodb.gridfs.GridFSFile;

public class UploadThread extends Thread {
	private GridFsOperations gridFsTemplate;
	private InputStream inputStream;
	private String fileName;
	private String contentType;
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	
	public UploadThread(GridFsOperations gridFsTemplate, InputStream inputStream, String fileName, String contentType) {
		super();
		this.gridFsTemplate = gridFsTemplate;
		this.inputStream = inputStream;
		this.fileName = fileName;
		this.contentType = contentType;
	}

	@Override
	public void run() {
		logger.info("start upload file");
		GridFSFile gridfile =gridFsTemplate.store(inputStream, fileName, contentType,null);
		logger.info("upload file complete");
	}

	public GridFsOperations getGridFsTemplate() {
		return gridFsTemplate;
	}

	public void setGridFsTemplate(GridFsOperations gridFsTemplate) {
		this.gridFsTemplate = gridFsTemplate;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}
