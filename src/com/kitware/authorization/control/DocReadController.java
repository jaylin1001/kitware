package com.kitware.authorization.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.A.control.Controller;
import com.kitware.authorization.service.DocSelectService;
import com.kitware.authorization.vo.DocDetailVO;
import com.kitware.authorization.vo.DocVO;
import com.kitware.member.vo.Members;

public class DocReadController implements Controller {
	DocSelectService service;
	
	public DocSelectService getService() {
		return service;
	}

	public void setService(DocSelectService service) {
		this.service = service;
	}

	public DocReadController() {
		super();
	}

	public DocReadController(DocSelectService service) {
		super();
		this.service = service;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String forwardURL = null;
		DocVO docvo_list = null;
		Members loginInfo = (Members) session.getAttribute("loginInfo");
		String emp_num = loginInfo.getEmp_num();
		String doc_num = request.getParameter("doc_num");
		String doc_kind = request.getParameter("doc_kind");
		System.out.println("로그인번호" + emp_num);
		System.out.println("문서번호" + doc_num);

		try {
			DocVO list = service.selectAll(doc_num);
			if(list.getRefer() == null) {
				docvo_list = service.selectAll(doc_num);
			}else {
				docvo_list = service.selectAllRefer(doc_num);
			}
			List<DocDetailVO> doc_detail_list = service.selectConf(doc_num);
			request.setAttribute("docvo_list", docvo_list);
			request.setAttribute("doc_detail_list", doc_detail_list);
			if(doc_kind.equals("40")) {
				// 출장
				forwardURL = "/authorization/chuljangread.jsp";
			} else if (doc_kind.equals("60")||doc_kind.equals("80")) {
				// 병가
				forwardURL = "/authorization/jotaeRead.jsp";
			} else { 
				//기안, 품의, 발주
				forwardURL = "/authorization/docread.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("result", e.getMessage());
			e.printStackTrace();
		}
		return forwardURL;
	}
}