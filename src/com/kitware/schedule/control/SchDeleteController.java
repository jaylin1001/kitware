package com.kitware.schedule.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.A.control.Controller;
import com.kitware.member.vo.Members;
import com.kitware.schedule.service.SchDMLService;
import com.kitware.schedule.vo.Schedule;

public class SchDeleteController implements Controller {
	private SchDMLService service;
	
	public SchDeleteController() {
		
	}

	public SchDMLService getService() {
		return service;
	}

	public void setService(SchDMLService service) {
		this.service = service;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String schno= request.getParameter("schno");
		Schedule schedule = new Schedule();
		schedule.setSch_no(schno);
		System.out.println(schedule.getSch_no());
		
		try {
			service.scheduleDelete(schedule);
			request.setAttribute("result", "1");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "-1"); //시스템상 에러
		}
		String forwardURL = "/schedule/scheduleDMLresult.jsp";
		return forwardURL;
	}
}
