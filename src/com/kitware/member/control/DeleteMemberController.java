package com.kitware.member.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.A.control.Controller;
import com.kitware.member.service.MemberService;
import com.kitware.member.vo.Members;
import com.kitware.member.vo.MembersDetailInfo;

public class DeleteMemberController implements Controller{
	private MemberService service;
	String forwardURL;
	public DeleteMemberController() {		
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
		String emp_num = request.getParameter("emp_num");		
		
		try {
			service.deleteMembers(emp_num);	
			request.setAttribute("result", 1);
		} catch (Exception e) {		
			e.printStackTrace();
			request.setAttribute("result", -1);
		}
		
		forwardURL = "modmember/deletememberresult.jsp";
		return forwardURL;
	}

}
