package com.kitware.board.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.A.control.Controller;
import com.kitware.board.service.BoardService;
import com.kitware.board.vo.NoticeBoard;
import com.kitware.member.vo.Members;
import com.kitware.schedule.vo.Schedule;

public class BoardEditController implements Controller {
	private BoardService service;
	
	
	public BoardEditController() {
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
		
		String seq= request.getParameter("seq");
		String title= request.getParameter("title");
		String content=request.getParameter("content");
		
		NoticeBoard noticeBoard = new NoticeBoard();
		
		noticeBoard.setSeq(seq);
		noticeBoard.setTitle(title);
		noticeBoard.setContent(content);
		try {
			service.updateNoticeBoard(noticeBoard);
			request.setAttribute("result", "1");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "-1"); //시스템상 에러
		}
		String forwardURL = "/board/editresult.jsp";
		return forwardURL;
	}

}
