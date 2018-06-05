package com.kitware.member.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.A.control.Controller;
import com.kitware.member.service.MemberService;
import com.kitware.member.vo.Members;

public class LoginController implements Controller {
	private MemberService service;

	
	public LoginController() {
	}
	

	public LoginController(MemberService service) {
		this.service = service;
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
		String pwdValue = request.getParameter("pwd");
		String pathValue = request.getContextPath();
		HttpSession session = request.getSession();
		session.invalidate();
		
		try {
			Members mb = service.login(idValue, pwdValue);
			if(mb != null) { //로그인 성공: Members, 실패:-1
				session = request.getSession();
				session.setAttribute("loginInfo", mb);			
			}
			request.setAttribute("result", "-1"); //로그인 실패
		}catch(Exception e) {
			request.setAttribute("result", e.getMessage());
		}
		String forwardURL = "/loginresult.jsp";
		return forwardURL;
	}
}
