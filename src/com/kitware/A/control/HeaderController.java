package com.kitware.A.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.authorization.service.DocSelectService;
import com.kitware.authorization.vo.DocVO;
import com.kitware.board.vo.NoticeBoard;
import com.kitware.member.vo.Members;
import com.kitware.schedule.service.SchCodeService;
import com.kitware.schedule.vo.Schedule;

public class HeaderController implements Controller {
	DocSelectService service;
	SchCodeService sservice;

	public DocSelectService getService() {
		return service;
	}

	public void setService(DocSelectService service) {
		this.service = service;
	}

	public SchCodeService getSservice() {
		return sservice;
	}

	public void setSservice(SchCodeService sservice) {
		this.sservice = sservice;
	}

	public HeaderController() {
		super();
	}

	public HeaderController(DocSelectService service) {
		super();
		this.service = service;
	}

	public HeaderController(SchCodeService sservice) {
		super();
		this.sservice = sservice;
	}

	public HeaderController(DocSelectService service, SchCodeService sservice) {
		super();
		this.service = service;
		this.sservice = sservice;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Members loginInfo = (Members)session.getAttribute("loginInfo");	
		String emp_num = loginInfo.getEmp_num();
		
		try {
			int doc_list =  service.selectGJWait(emp_num).size();
			int listSchedule = sservice.findSchPersonal(emp_num).size();
			session.setAttribute("doc_list", doc_list);
			session.setAttribute("schedule", listSchedule);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String forwardURL = "/container/header.jsp";
		return forwardURL;
	}

}