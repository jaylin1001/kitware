package com.kitware.authorization.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.A.control.Controller;
import com.kitware.authorization.service.DocSelectService;
import com.kitware.authorization.vo.DocVO;
import com.kitware.member.vo.Members;
import com.sun.javafx.collections.SetAdapterChange;

public class DocListController implements Controller {
	private DocSelectService service;

	public DocListController() {
		super();
	}
	public DocListController(DocSelectService service) {
		super();
		this.service = service;
	}
	
	public DocSelectService getService() {
		return service;
	}
	public void setService(DocSelectService service) {
		this.service = service;
	} 
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Members loginInfo = (Members)session.getAttribute("loginInfo");	
		String emp_num = loginInfo.getEmp_num();
		System.out.println("로그인번호"+emp_num);
		int page = 1;
		
		try {
			List<DocVO> docvo_list0 = service.selectGJWait(emp_num, page);
			request.setAttribute("docvo_list0", docvo_list0);
			
			List<DocVO> docvo_list = service.findIng(emp_num, page); 
			request.setAttribute("docvo_list", docvo_list);
			
			List<DocVO> docvo_list2 = service.findOk(emp_num, page); 
			request.setAttribute("docvo_list2", docvo_list2);
			
		} catch (Exception e) {
			request.setAttribute("result", e.getMessage());
			e.printStackTrace();
		}
		
		String forwardURL = "/authorization/approveMain.jsp";
		return forwardURL;
	}

}
