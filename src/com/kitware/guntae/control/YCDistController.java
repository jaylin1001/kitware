package com.kitware.guntae.control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.A.control.Controller;
import com.kitware.guntae.service.GTService;
import com.kitware.guntae.vo.Yeoncha;
import com.kitware.member.vo.Members;

public class YCDistController implements Controller {
	private GTService service;
	SimpleDateFormat format = new SimpleDateFormat("yyyy");

	public YCDistController() {
	}

	public GTService getService() {
		return service;
	}

	public void setService(GTService service) {
		this.service = service;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// session 받아서 쓰기 (생성x)
		HttpSession session = request.getSession();
		Members loginInfo = (Members) session.getAttribute("loginInfo");
		String emp_num = loginInfo.getEmp_num();
		String years = request.getParameter("years");

		if (years == null) {
			years = format.format(new Date());
		}
		try {
			// 연차 모두 셀렉트
			List<Yeoncha> yeonchalist = service.selectAll(emp_num, years);
			request.setAttribute("yclist", yeonchalist);
			int useyc = 0;
			for (Yeoncha yc : yeonchalist) {
				useyc+=yc.getUse_yeoncha();
			}
			request.setAttribute("use", Integer.toString(useyc));
			System.out.println("사용연차아아!!!!!!!!!"+Integer.toString(useyc));

			// System.out.println(yeonchalist.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String forwardURL = "attendance/myyeoncha.jsp?years=" + years;
		return forwardURL;
	}

}
