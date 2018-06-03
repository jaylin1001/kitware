package com.kitware.board.control;

import java.io.File;
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
import com.oreilly.servlet.MultipartRequest;

public class BoardEditController implements Controller {
	private BoardService service;
	private String saveFileName;
	private String originFileName;
	private File file1;
	private String hitseq;
	private String seq;
	private String title;
	private String content;

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
		String saveDirectory = "D:\\apache-tomcat-8.5.30\\webapps\\upload";
		String delseq = request.getParameter("delseq");
		
		NoticeBoard noticeBoard = new NoticeBoard();
		
		int maxPostSize = 1024 * 2000000;
		String encoding = "UTF-8";

		// 객체가 생성됨과 동시에 파일업로드가 이루어짐.
		if(delseq == null) {
			// 파일첨부
			MultipartRequest mr;
			mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, new MyRenamePolicy());
			file1 = mr.getFile("file1");
			if(file1 != null) {//새롭게 변경할 파일이 있다면
				saveFileName = file1.getName();
				int indexExt = saveFileName.lastIndexOf(".");
				int index_ = saveFileName.lastIndexOf("_");
				String ext = saveFileName.substring(indexExt); // .txt , .jpg  확장자
				String fileName = saveFileName.substring(0, index_); // 확장자 없는 원본 파일명
				originFileName = fileName+ext;
				noticeBoard.setOriginFileName(originFileName);
				noticeBoard.setSaveFileName(saveFileName);
				noticeBoard.setPath(saveDirectory+"\\"+saveFileName);	
			}
			hitseq = mr.getParameter("hitseq");
			seq = mr.getParameter("seq");
			title = mr.getParameter("title");
			content = mr.getParameter("content");
			noticeBoard.setSeq(seq);
			noticeBoard.setTitle(title);
			noticeBoard.setContent(content);
		}
		
		
		try {
			if (delseq != null) {
				service.deleteNoticeBoard(delseq);
			}else {
				if(file1 == null){ //새로 등록한 파일이 없을 때는 제목,내용만 수정
					service.updateNoticeBoard(noticeBoard);
					System.out.println("여기를 통과했니? 파일이 없을때임...");
				}else {// 새로 등록한 파일이 존재할 때는 제목,내용,파일까지 전부 수정.
					service.updateNoticeBoardFile(noticeBoard);
					System.out.println("여기를 통과했니? 여기는 새로운 파일이 있을때임...");
				}
			}
			
			if (hitseq != null) {
				service.updateHit(hitseq);
			}
			
			request.setAttribute("result", "1");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "-1"); // 시스템상 에러
		}
		String forwardURL = "/board/editresult.jsp";
		return forwardURL;
	}

}
