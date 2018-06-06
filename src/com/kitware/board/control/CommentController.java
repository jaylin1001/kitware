package com.kitware.board.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.A.control.Controller;
import com.kitware.board.service.BoardService;
import com.kitware.board.vo.Comment;
import com.kitware.member.vo.Members;

public class CommentController implements Controller {
	BoardService service = BoardService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Members loginInfo = (Members) request.getSession().getAttribute("loginInfo");
		String type = request.getParameter("type");
		String forwardURL = "";
		if ("read".equals(type)) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			List<Comment> comlist = new ArrayList<>();
			try {
				comlist = service.getComment(seq);
				request.setAttribute("comlist", comlist);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			forwardURL = "board/comment.jsp";
		} else if ("write".equals(type)) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			String content = request.getParameter("content");
			try {
				service.insertComment(seq, loginInfo, content);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("del".equals(type)) {
			 int seq = Integer.parseInt(request.getParameter("seq"));
			 try {
				service.delComment(seq);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return forwardURL;
	}

}
