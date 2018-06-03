package com.kitware.board.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.A.control.Controller;
import com.kitware.board.service.BoardService;
import com.kitware.board.vo.NoticeBoard;

public class BoardPreNextController implements Controller {
	private BoardService service;
	public BoardPreNextController() {
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
		
		String seq = request.getParameter("seq");
		String flag = request.getParameter("flag"); // flag가 0이면 이전글 service flag가 1이면 다음글  service
		
		try {
			if(flag.equals("0")) {
				NoticeBoard nb = service.findPre(seq);
				if(nb.getSeq() != null) {
					service.updateHit(nb.getSeq()); //받아온 이전글 조회수 증가!
					request.setAttribute("prePost", nb);
				}else {// seq 값이 null 이면 이전글이 없다.
					request.setAttribute("error", "이전글이 없습니다.");
				}
			}else if(flag.equals("1")){
				NoticeBoard nb = service.findNext(seq);
				if(nb.getSeq() != null) {
					service.updateHit(nb.getSeq()); //받아온 다음글 조회수 증가!
					request.setAttribute("nextPost", nb);
				}else {// seq 값이 null 이면 다음글이 없다.
					request.setAttribute("error", "다음글이 없습니다.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String forwardURL = "/board/content_conf.jsp";
		return forwardURL;
	}

}
