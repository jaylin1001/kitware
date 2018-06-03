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

public class SchQuickAddController implements Controller {
	private SchDMLService service;

	public SchQuickAddController() {
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
		// 로그인 세션값 가져오기.
		HttpSession session = request.getSession();
		Members loginInfo = (Members) session.getAttribute("loginInfo");
		String emp_num = loginInfo.getEmp_num();
		
		String title= request.getParameter("title");
		String startdate= request.getParameter("start");
		String enddate= request.getParameter("end");
		String schcode=request.getParameter("code");
		
		Schedule schedule = new Schedule();
		
		schedule.setEmp_num(emp_num);
		schedule.setSch_name(title);
		schedule.setSch_startdate(startdate);
		schedule.setSch_enddate(enddate);
		schedule.setSch_code(schcode);
		try {
			service.scheduleQuickAdd(schedule);
			request.setAttribute("result", "1");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "-1"); //시스템상 에러
		}
		String forwardURL = "/schedule/scheduleDMLresult.jsp";
		return forwardURL;
	}

}
