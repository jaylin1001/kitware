package com.kitware.A.filter;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.authorization.service.DocSelectService;
import com.kitware.member.service.MemberService;
import com.kitware.member.vo.Mail;
import com.kitware.member.vo.Members;
import com.kitware.schedule.service.SchCodeService;
import com.kitware.schedule.vo.Schedule;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class CharEncodingFilter implements Filter {
	String encoding;
	private List<String> permitList;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar c1 = Calendar.getInstance();

	public CharEncodingFilter() {
		// 로그인 유무를 묻지 않는 페이지들 guest 상태를 허용한다.
		permitList = new ArrayList<String>();
		permitList.add("/login.do");
		permitList.add("/login.jsp");
		permitList.add("/loginresult.jsp");

	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		String uri = req.getServletPath();
		String contextPath = req.getContextPath();
		DocSelectService service = DocSelectService.getInstance();
		SchCodeService sservice = SchCodeService.getInstance();
		MemberService mservice = MemberService.getInstance();
		// permit 리스트에 요청한 uri 값이 없다면 실행
		if (!permitList.contains(uri)) {
			HttpSession session = req.getSession();
			Members mb = (Members) session.getAttribute("loginInfo");
			if (mb == null) {
				HttpServletResponse res = (HttpServletResponse) response;
				res.sendRedirect(contextPath);
				return;
			}else {
				try {
					String date = sdf.format(c1.getTime());
					int doc_list = service.selectGJWait(mb.getEmp_num()).size();
					List<Schedule> listSchedule = sservice.findSchPersonalToday(mb.getEmp_num(), date);
					Mail mail_list = mservice.selectMailList3(mb.getEmp_num());
					session.setAttribute("mail_list", mail_list);
					session.setAttribute("doc_list", doc_list);
					session.setAttribute("schedule", listSchedule);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
	}
}
