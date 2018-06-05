package com.kitware.board.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.A.control.Controller;
import com.kitware.board.service.BoardService;
import com.kitware.board.vo.NoticeBoard;
import com.kitware.board.vo.PageBean;


public class BoardListController implements Controller {
	private BoardService service;
	String forwardURL;
	int totalCount;
	public BoardListController() {
	}

	
	public BoardService getService() {
		return service;
	}


	public void setService(BoardService service) {
		this.service = service;
	}


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getParameter("page");
		
		int intPage = 1;
		int flag = 0; //첫 요청일 경우 0 아닐 경우 1
		if(page != null) {
			intPage = Integer.parseInt(page);
			flag = 1;
		}try {
			//게시물 총목록수
			totalCount = service.findCount();
			//총페이지수계산
			int totalPage = 0;
			int cntPerPage=4;//1페이지별 5건씩 보여준다
			totalPage = (int)Math.ceil((double)totalCount/ cntPerPage);
			//페이지그룹에서 쓰일 시작페이지값, 끝페이지값계산
			int cntPerPageGroup=5; //페이지그룹별 5페이지씩 보여준다
			int startPage = (int)Math.floor((double)(intPage)/(cntPerPageGroup+1))*cntPerPageGroup+1;
			int endPage = startPage+cntPerPageGroup-1;
			if(endPage > totalPage) {
				endPage = totalPage;
			}	
			List<NoticeBoard> list = service.findAll(intPage);
			PageBean<NoticeBoard> pb = new PageBean<>();
			pb.setCurrentPage(intPage);//현재페이지
			pb.setTotalPage(totalPage); //총페이지
			pb.setList(list); //목록
			pb.setStartPage(startPage); //시작페이지
			pb.setEndPage(endPage); //끝페이지
			pb.setTotalCount(totalCount); //총 게시글 갯수
			pb.setCntPerPage(cntPerPage);
			/*System.out.println("현재페이지:"+intPage);
			System.out.println("총페이지:"+totalPage);
			System.out.println("시작페이지:"+startPage);
			System.out.println("끝페이지:"+endPage);*/
			request.setAttribute("pagebean", pb);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", e.getMessage());
		}
		if(flag == 0) {
			forwardURL = "/board/board.jsp";
		}else {
			forwardURL = "/board/nboardresult.jsp";
		}
		return forwardURL;
	}

}
