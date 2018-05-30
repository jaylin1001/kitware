package com.kitware.authorization.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.authorization.service.DocSelectService;
import com.kitware.authorization.vo.DocDetailVO;
import com.kitware.authorization.vo.DocVO;
import com.kitware.member.vo.Members;

public class DocReadCJController {
	DocSelectService service = new DocSelectService();

	public DocReadCJController() {
		super();
	}

	public DocReadCJController(DocSelectService service) {
		super();
		this.service = service;
	}

	public DocSelectService getService() {
		return service;
	}

	public void setService(DocSelectService service) {
		this.service = service;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Members loginInfo = (Members) session.getAttribute("loginInfo");
		String emp_num = loginInfo.getEmp_num();
		String doc_num = request.getParameter("doc_num");
		System.out.println("로그인번호" + emp_num);
		System.out.println("문서번호" + doc_num);
		
		try {
			DocVO docvo_list = service.selectAll(doc_num);
			request.setAttribute("docvo_list", docvo_list);
			System.out.println(docvo_list);
			
			List<DocDetailVO> doc_detail_list = service.selectConf(doc_num);
			request.setAttribute("doc_detail_list", doc_detail_list);
			System.out.println(doc_detail_list);

		} catch (Exception e) {
			request.setAttribute("result", e.getMessage());
			e.printStackTrace();
		}

		String forwardURL = "/authorization/chuljangread.jsp";
		return forwardURL;
	}
}
