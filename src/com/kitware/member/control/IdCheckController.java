package com.kitware.member.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.A.control.Controller;
import com.kitware.member.service.MemberService;

public class IdCheckController implements Controller{
	private MemberService service;
	String forwardURL;
	public IdCheckController() {
	}

	public MemberService getService() {
		return service;
	}

	public void setService(MemberService service) {
		this.service = service;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idValue = request.getParameter("id");		
		try {
			String result = service.idCheck(idValue);
			request.setAttribute("result", result);
		}catch(Exception e) {
			request.setAttribute("result", e.getMessage());
		}	
		String forwardURL = "idcheckresult.jsp";
		return forwardURL;
	}

}
