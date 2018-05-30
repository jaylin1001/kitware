package com.kitware.board.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.A.control.Controller;
import com.kitware.board.service.BoardService;
import com.kitware.board.vo.NoticeBoard;
import com.kitware.board.vo.PageBean;
import com.kitware.member.vo.Members;
import com.kitware.schedule.vo.Schedule;

public class BoardWriteController implements Controller {
	private BoardService service;
	
	public BoardWriteController() {
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
		request.setCharacterEncoding("UTF-8");
		//로그인 세션값 가져오기.
		HttpSession session = request.getSession();
		Members loginInfo = (Members)session.getAttribute("loginInfo");	
		String emp_num = loginInfo.getEmp_num();
		String name = loginInfo.getName();
		

		String title= request.getParameter("title");
		String content= request.getParameter("content");
		
		NoticeBoard noticeBoard = new NoticeBoard();
		
		noticeBoard.setEmp_num(emp_num);
		noticeBoard.setName(name);
		noticeBoard.setTitle(title);
		noticeBoard.setContent(content);
		
		
		try {
			service.insertNoticeBoard(noticeBoard);
			request.setAttribute("result", "1");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "-1"); //시스템상 에러
		}
		String forwardURL = "/board/writeresult.jsp";
		return forwardURL;
	}

}
