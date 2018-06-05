package com.kitware.member.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.A.control.Controller;
import com.kitware.member.service.MemberService;
import com.kitware.member.vo.Members;

public class AddMemberController implements Controller {
	private MemberService service;
	String forwardURL;

	public AddMemberController() {
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
		try {
			Members mb = service.findMaxEmum();
			request.setAttribute("result", mb);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", e.getMessage());
		}

		forwardURL = "/modmember/addmember.jsp";
		return forwardURL;
	}

}
