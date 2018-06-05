package com.kitware.member.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.A.control.Controller;
import com.kitware.member.service.MemberService;
import com.kitware.member.vo.StatusDetailBoard;

public class MemberDetailController implements Controller{
	private MemberService service;
	String forwardURL;
	public MemberDetailController() {		
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
		request.setCharacterEncoding("UTF-8");		
		String emp = request.getParameter("emp_num");	
		int emp_num = Integer.parseInt(emp);
		try {
			StatusDetailBoard sdb = service.findDetail(emp_num);
			request.setAttribute("result", sdb);
		} catch (Exception e) {			
			e.printStackTrace();
			request.setAttribute("result", e.getMessage());
		}
		
		forwardURL="/modmember/memberstatusdetailresult.jsp";
		return forwardURL;		
	}
}