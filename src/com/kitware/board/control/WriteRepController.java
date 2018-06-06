package com.kitware.board.control;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.A.control.Controller;
import com.kitware.board.service.BoardService;
import com.kitware.board.vo.DepartBoard;
import com.kitware.member.vo.Members;
import com.oreilly.servlet.MultipartRequest;

public class WriteRepController implements Controller {
	private BoardService service;
	private String saveFileName;
	private String originFileName;

	public WriteRepController() {
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
		String contextPath = request.getSession().getServletContext().getRealPath("/");
		String saveDirectory = "E:\\apache-tomcat-8.5.30-windows-x64\\apache-tomcat-8.5.30\\webapps\\upload";

		// 로그인 세션값 가져오기.
		HttpSession session = request.getSession();
		Members loginInfo = (Members) session.getAttribute("loginInfo");
		String emp_num = loginInfo.getEmp_num();
		String name = loginInfo.getName();

		DepartBoard departBoard = new DepartBoard();
		// 파일첨부
		MultipartRequest mr;
		int maxPostSize = 1024 * 2000000;
		String encoding = "UTF-8";
		// 객체가 생성됨과 동시에 파일업로드가 이루어짐.
		mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, new MyRenamePolicy());

		File file1 = mr.getFile("file1"); // 저장된 파일
		if (file1 != null) {
			saveFileName = file1.getName();
			int indexExt = saveFileName.lastIndexOf(".");
			int index_ = saveFileName.lastIndexOf("_");
			String ext = saveFileName.substring(indexExt); // .txt , .jpg 확장자
			String fileName = saveFileName.substring(0, index_); // 확장자 없는 원본 파일명
			originFileName = fileName + ext;
			departBoard.setOriginFileName(originFileName);
			departBoard.setSaveFileName(saveFileName);
			departBoard.setPath(saveDirectory + "\\" + saveFileName);
		}

		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String seq = mr.getParameter("seq");
		String level = mr.getParameter("level");

		departBoard.setEmp_num(emp_num);
		departBoard.setName(name);
		departBoard.setDept_num(Integer.parseInt(loginInfo.getDept_num()));
		departBoard.setTitle(title);
		departBoard.setContent(content);
		departBoard.setP_seq(Integer.parseInt(seq));

		try {
			service.insertDeptBoard(departBoard);
			request.setAttribute("result", "1");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "-1"); // 시스템상 에러
		}
		String forwardURL = "/board/writeresult.jsp";
		return forwardURL;
	}

}
