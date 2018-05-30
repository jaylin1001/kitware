package com.kitware.authorization.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.member.vo.Members;

public class DocWriteCJController {
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	HttpSession session = request.getSession();
	Members loginInfo = (Members)session.getAttribute("loginInfo");	
	String emp_num = loginInfo.getEmp_num();
	System.out.println("로그인번호"+emp_num);
	
	
	
	String forwardURL = "/authorization/chuljang.jsp";
	return forwardURL;
	}
}
