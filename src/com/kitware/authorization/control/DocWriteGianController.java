package com.kitware.authorization.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.A.control.Controller;
import com.kitware.authorization.service.DocManipulService;
import com.kitware.authorization.vo.DocDetailVO;
import com.kitware.authorization.vo.DocVO;
import com.kitware.member.vo.Members;

public class DocWriteGianController implements Controller {
	DocManipulService service = DocManipulService.getInstance();

	public DocWriteGianController() {
		// TODO Auto-generated constructor stub
	}

	public DocWriteGianController(DocManipulService service) {
		super();
		this.service = service;
	}

	public DocManipulService getService() {
		return service;
	}

	public void setService(DocManipulService service) {
		this.service = service;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<DocDetailVO> list = new ArrayList<>();
		DocDetailVO docdetail = new DocDetailVO();
		HttpSession session = request.getSession();
		Members member = (Members) session.getAttribute("loginInfo");
		String doc_num = request.getParameter("doc_num");
		System.out.println(doc_num);
		String emp_num = member.getEmp_num();
		String date = request.getParameter("date");
		System.out.println(date);
		String dept = request.getParameter("dept");
		System.out.println(dept);
		String title = request.getParameter("title");
		System.out.println(title);
		String content = request.getParameter("content");
		System.out.println(content+"kkk");
		String g1_grade = request.getParameter("g1_grade");
		String g1 = request.getParameter("g1");
		System.out.println(g1_grade);
		System.out.println(g1);
		String g2_grade = request.getParameter("g2_grade");
		String g2 = request.getParameter("g2");
		String g3_grade = request.getParameter("g3_grade");
		String g3 = request.getParameter("g3");

		try {
			if (g1_grade != null) {
				docdetail.setDoc_num(doc_num);
				docdetail.setConf_num(service.getEmpNum(g1, g1_grade));
				list.add(docdetail);
			}
			if (g2_grade != null)
				docdetail.setDoc_num(doc_num);
			docdetail.setConf_num(service.getEmpNum(g1, g1_grade));
			list.add(docdetail);
			{

			}
			if (g3_grade != null) {
				docdetail.setDoc_num(doc_num);
				docdetail.setConf_num(service.getEmpNum(g1, g1_grade));
				list.add(docdetail);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DocVO giandoc = new DocVO();
		giandoc.setDoc_num(doc_num);
		giandoc.setEmp_num(emp_num);
		giandoc.setStart_date(date);
		giandoc.setRcv_dept(dept);
		giandoc.setDoc_title(title);
		giandoc.setDoc_content(content);
		giandoc.setDoc_detail(list);
		
		
		try {
			service.insertgian(giandoc);
			request.setAttribute("result", 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result", -1);
		}
		String forwardURL = "/docresult.jsp";
		return forwardURL;
	}

}
