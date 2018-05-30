package com.kitware.schedule.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.A.control.Controller;
import com.kitware.member.vo.Members;
import com.kitware.schedule.vo.Schedule;
import com.kitware.schedule.service.SchCodeService;

public class SchCompanyController implements Controller {

	private SchCodeService service;
	
	public SchCompanyController() {
	}
	
	public SchCodeService getService() {
		return service;
	}


	public void setService(SchCodeService service) {
		this.service = service;
	}


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		Members loginInfo = (Members)session.getAttribute("loginInfo");	
		String emp_num = loginInfo.getEmp_num();
		try {
			List<Schedule> listSchedule = service.findSchCompany(emp_num);
			request.setAttribute("schedule", listSchedule);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		String forwardURL = "schedule/schfindresult.jsp";
		return forwardURL;
	}

}
