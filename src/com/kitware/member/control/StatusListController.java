package com.kitware.member.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.A.control.Controller;
import com.kitware.member.service.MemberService;
import com.kitware.member.vo.PageBean;
import com.kitware.member.vo.StatusBoard;

public class StatusListController implements Controller{
	private MemberService service;
	String forwardURL;
	int totalCount;
	public StatusListController() {		
	}
	public MemberService getService() {
		return service;
	}
	public void setService(MemberService service) {
		this.service = service;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getParameter("page");
		
		int intPage=1;
		int flag = 0;
		if (page != null) {
			intPage =Integer.parseInt(page);
			flag = 1;
		} try {
			totalCount = service.findCount();
			int totalPage =0;
			int cntPerPage = 7;
			totalPage = (int)Math.ceil((double)totalCount/cntPerPage);
			int cntPerPageGroup =5;
			int startPage = (int)Math.floor((double)(intPage)/(cntPerPageGroup+1))*cntPerPageGroup+1;
			int endPage = startPage + cntPerPageGroup -1;
			if(endPage>totalPage) {
				endPage = totalPage;
			}
			List<StatusBoard> list = service.findAll(intPage);
			PageBean<StatusBoard> pb = new PageBean<>();
			System.out.println("controllerlist"+list);
			pb.setCurrentPage(intPage);//현재페이지
			pb.setTotalPage(totalPage); //총페이지
			pb.setList(list); //목록
			pb.setStartPage(startPage); //시작페이지
			pb.setEndPage(endPage); //끝페이지
			pb.setTotalCount(totalCount); //총 게시글 갯수
			pb.setCntPerPage(cntPerPage);
			request.setAttribute("pagebean", pb);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", e.getMessage());
		}
		if(flag == 0) {
			forwardURL = "/modmember/memberstatus.jsp";
		}else {
			forwardURL = "/modmember/memberstatusresult.jsp";
		}
		return forwardURL;
	}


}
