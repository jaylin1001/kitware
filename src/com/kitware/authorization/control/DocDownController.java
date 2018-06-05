package com.kitware.authorization.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.A.control.Controller;
import com.kitware.authorization.service.DocSelectService;

public class DocDownController implements Controller {
	private DocSelectService service;

	public DocSelectService getService() {
		return service;
	}

	public void setService(DocSelectService service) {
		this.service = service;
	}

	public DocDownController() {
		super();
	}

	public DocDownController(DocSelectService service) {
		super();
		this.service = service;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path = request.getParameter("path");
		String originFName = request.getParameter("originFName");
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		//응답내용크기설정
		response.setContentLength((int)file.length());
		//응답형식형식지정
		response.setHeader("Content-Type", "application/octet-stream");
		//다운로드파일이름 설정
		response.setHeader("Content-Disposition",
				"attachment; filename="+ new String(originFName.getBytes("UTF-8"), "ISO-8859-1")+";");

		ServletOutputStream os = response.getOutputStream();
		int readValue = -1;
		while((readValue = fis.read()) != -1) {
			os.write(readValue);
		};
		os.close();
		return "";
	}

}
