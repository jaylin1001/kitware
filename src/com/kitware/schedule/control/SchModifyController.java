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

public class SchModifyController implements Controller {
	private SchDMLService service;
	
	public SchModifyController() {
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
		
		//로그인 세션값 가져오기.
		HttpSession session = request.getSession();
		Members loginInfo = (Members)session.getAttribute("loginInfo");	
		String emp_num = loginInfo.getEmp_num();
		
		String schno= request.getParameter("schno");
		String title= request.getParameter("title");
		String schsp= request.getParameter("schtype");
		String startdate= request.getParameter("startdate");
		String starthour= request.getParameter("starthour");
		String startminute= request.getParameter("startminute");
		String enddate= request.getParameter("enddate");
		String endhour= request.getParameter("endhour");
		String endminute= request.getParameter("endminute");
		String contents=request.getParameter("contents");
		
		Schedule schedule = new Schedule();
		
		schedule.setSch_no(schno);
		schedule.setSch_name(title);
		schedule.setSch_type(schsp);
		schedule.setSch_startdate(startdate);
		schedule.setSch_starthour(starthour);
		schedule.setSch_startmin(startminute);
		schedule.setSch_enddate(enddate);
		schedule.setSch_endhour(endhour);
		schedule.setSch_endmin(endminute);
		schedule.setSch_contents(contents);
		
		try {
			service.scheduleUpdate(schedule);
			request.setAttribute("result", "1");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "-1"); //시스템상 에러
		}
		String forwardURL = "/schedule/scheduleDMLresult.jsp";
		return forwardURL;
		
	}

}
