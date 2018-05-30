package com.kitware.authorization.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.A.control.Controller;
import com.kitware.authorization.service.DocSelectService;
import com.kitware.authorization.vo.DocVO;
import com.kitware.authorization.vo.PageBean;
import com.kitware.member.vo.Members;

public class DocGJWaitController4 implements Controller{
	private DocSelectService service;	
	
	public DocGJWaitController4() {
		super();
	}
	
	public DocGJWaitController4(DocSelectService service) {
		super();
		this.service = service;
	}

	public DocSelectService getService() {
		return service;
	}

	public void setService(DocSelectService service) {
		this.service = service;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Members loginInfo = (Members)session.getAttribute("loginInfo");	
		String emp_num = loginInfo.getEmp_num();
		String page = request.getParameter("page");
		System.out.println("로그인번호"+emp_num);
		
		int intPage = 1;
		if (page != null) {
			intPage = Integer.parseInt(page);
			System.out.println("페이지가져온거" + page);
		}
		try {
			// 게시물 총목록수
			int totalCount = service.findCount();

			// 총페이지수계산
			int totalPage = 0;
			int cntPerPage = 5;// 1페이지별 5건씩 보여준다
			totalPage = (int) Math.ceil((double) totalCount / cntPerPage);

			// 페이지그룹에서 쓰일 시작페이지값, 끝페이지값계산
			int cntPerPageGroup = 1; // 페이지그룹별 1페이지씩 보여준다
			int startPage = (int) Math.floor((double) (intPage) / (cntPerPageGroup + 1)) * cntPerPageGroup + 1;
			int endPage = startPage + cntPerPageGroup - 1;
			int prePage = intPage-1;
			int nextPage = intPage+1;
			if (endPage > totalPage) {
				endPage = totalPage;
			}
			if(prePage <1) {
				prePage = 1;
			}
			
			
			List<DocVO> listok = service.selectCancle(emp_num, intPage);
			PageBean<DocVO> pb = new PageBean<>();
			pb.setCurrentPage(intPage);// 현재페이지
			pb.setTotalPage(totalPage); // 총페이지
			pb.setList(listok); // 목록
			pb.setStartPage(startPage); // 시작페이지
			pb.setEndPage(endPage); // 끝페이지

			request.setAttribute("pagebean", pb);
			request.setAttribute("prePage", prePage);
			request.setAttribute("nextPage", nextPage);
			request.setAttribute("nextPage", nextPage);
			
		
			System.out.println("intpage:" + intPage);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", e.getMessage());
		}
		String forwardURL = "/authorization/gj_wait_cancle.jsp";
		return forwardURL;
	}
}
