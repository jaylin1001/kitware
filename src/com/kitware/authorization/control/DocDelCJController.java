package com.kitware.authorization.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.A.control.Controller;
import com.kitware.authorization.service.DocManipulService;
import com.kitware.authorization.vo.DocVO;
import com.kitware.authorization.vo.PageBean;
import com.kitware.member.vo.Members;

public class DocDelCJController implements Controller {
	DocManipulService service;
	public DocDelCJController(DocManipulService service) {
		super();
		this.service = service;
	}
	public DocDelCJController() {
		super();
	}
	public DocManipulService getService() {
		return service;
	}
	public void setService(DocManipulService service) {
		this.service = service;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Members loginInfo = (Members)session.getAttribute("loginInfo");	
		String emp_num = loginInfo.getEmp_num();
		String doc_num = request.getParameter("doc_num");
		System.out.println("로그인번호"+emp_num);
		
		try {
			service.deleteDoc(doc_num);
			request.setAttribute("result", "1");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", e.getMessage());
		}
		String forwardURL = "authorization/delresult.jsp";
		return forwardURL;
	}
	
}
