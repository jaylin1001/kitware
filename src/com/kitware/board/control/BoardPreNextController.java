package com.kitware.board.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.A.control.Controller;
import com.kitware.board.service.BoardService;
import com.kitware.board.vo.NoticeBoard;
import com.kitware.board.vo.PhotoBoard;

public class BoardPreNextController implements Controller {
	private BoardService service;
	private String forwardURL;
	private PhotoBoard pb;
	private NoticeBoard nb;
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
		String prenext = request.getParameter("prenext"); // flag가 0,3이면 이전글 service flag가 1,4이면 다음글  service
																					
		
		try {
			if(prenext.equals("0")) {
				nb = service.findPre(seq);
				if(nb.getSeq() != null) {
					service.updateHit(nb.getSeq()); //받아온 이전글 조회수 증가!
					request.setAttribute("prePost", nb);
				}else {// seq 값이 null 이면 이전글이 없다.
					request.setAttribute("error", "이전글이 없습니다.");
				}
			}else if(prenext.equals("1")){
				nb = service.findNext(seq);
				if(nb.getSeq() != null) {
					service.updateHit(nb.getSeq()); //받아온 다음글 조회수 증가!
					request.setAttribute("nextPost", nb);
				}else {// seq 값이 null 이면 다음글이 없다.
					request.setAttribute("error", "다음글이 없습니다.");
				}
			}else if(prenext.equals("3")){
				pb = service.findPBPre(seq);
				if(pb.getSeq() != null) {
					service.updatePBHit(pb.getSeq()); //받아온 이전글 조회수 증가!
					request.setAttribute("prePost", pb);
				}else {// seq 값이 null 이면 이전글이 없다.
					request.setAttribute("error", "이전글이 없습니다.");
				}
			}else if(prenext.equals("4")){
				pb = service.findPBNext(seq);
				if(pb.getSeq() != null) {
					service.updatePBHit(pb.getSeq()); //받아온 다음글 조회수 증가!
					request.setAttribute("nextPost", pb);
				}else {// seq 값이 null 이면 다음글이 없다.
					request.setAttribute("error", "다음글이 없습니다.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(prenext.equals("3") || prenext.equals("4")) { //사진게시판일 경우 flag=1을 parameter로 보내야한다.
			forwardURL = "/board/content_conf.jsp?flag=1";
		}else {
			forwardURL = "/board/content_conf.jsp";
		}
		
		return forwardURL;
	}

}
