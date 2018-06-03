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

import com.kitware.member.vo.Members;

public class CharEncodingFilter implements Filter {
	String encoding;
	private List<String> permitList;

	public CharEncodingFilter() {
		//로그인 유무를 묻지 않는 페이지들 guest 상태를 허용한다.
		permitList = new ArrayList<String>();
		permitList.add("/login.do");
		permitList.add("/login.jsp");
		permitList.add("/loginresult.jsp");

	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;

		String uri = req.getServletPath();
		String contextPath = req.getContextPath();
		
		//permit 리스트에  요청한 uri 값이 없다면 실행
		if(!permitList.contains(uri)){
			HttpSession session = req.getSession();
			Members mb = (Members)session.getAttribute("loginInfo");
			if(mb == null){
				HttpServletResponse res = (HttpServletResponse)response;
				res.sendRedirect(contextPath);
				return;
			}
		}
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
	}
}
