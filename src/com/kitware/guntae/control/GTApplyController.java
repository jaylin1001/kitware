package com.kitware.guntae.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.A.control.Controller;
import com.kitware.authorization.vo.DocGiganVO;
import com.kitware.guntae.service.GTService;
import com.kitware.guntae.vo.Gunte;
import com.kitware.member.vo.Members;

public class GTApplyController implements Controller {
	private GTService service;

	public GTApplyController() {
		super();
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
		// emp_num 호출

		HttpSession session = request.getSession();
		Members loginInfo = (Members) session.getAttribute("loginInfo");
		String emp_num = loginInfo.getEmp_num();
		String mode = request.getParameter("mode");
		String years = request.getParameter("years");
		String months = request.getParameter("months");
		String type = request.getParameter("type");
		System.out.println(type+"타입!!!!!!!!!!!!!!");
		String forwardURL="";
		// 연차,출장,병가,조퇴
		// String countbyunggamonth = service.findbyungamonth(years, months);
		List<Integer> statuslist  = new ArrayList<>();
		if ("cnt".equals(type)) {
			try {
//				System.out.println(String.format("%02d", months));
				statuslist = service.getStatusCount(years, months,emp_num);
				request.setAttribute("statuslist", statuslist);
				forwardURL="/attendance/statusresult.jsp";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			try {
				if (mode != null) {
					if (mode.equals("in")) {
						// 근태 출석 인설트
						service.ininsert(emp_num);
						request.setAttribute("rs", "1");
					} else if (mode.equals("out")) {
						// 근태 퇴근 업데이트
						service.outupdate(emp_num);
						request.setAttribute("rs", "-1");

					}
				}
				request.setAttribute("mode", mode);

				// 근태 모든것 셀렉트
				List<Gunte> gslist = service.gselectAll(emp_num);
				request.setAttribute("Gunte", gslist);
				// System.out.println(gslist);

				List<DocGiganVO> chuljanglist = service.dockindselectAll(emp_num, "40");
				request.setAttribute("chuljanglist", chuljanglist);
				// System.out.println("출장"+chuljanglist);

				List<DocGiganVO> yeonchlist = service.dockindselectAll(emp_num, "50");
				request.setAttribute("yeonchlist", yeonchlist);

				List<DocGiganVO> bunggalist = service.dockindselectAll(emp_num, "60");
				request.setAttribute("bunggalist", bunggalist);

				List<DocGiganVO> jotaelist = service.dockindselectAll(emp_num, "80");
				request.setAttribute("jotaelist", jotaelist);

			} catch (Exception e) {
				e.printStackTrace();
			}
			forwardURL = "/attendance/gtresult.jsp";
		}
		
		return forwardURL;
	}
}
