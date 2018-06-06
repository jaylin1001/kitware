package com.kitware.board.control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.A.control.Controller;
import com.kitware.board.service.BoardService;
import com.kitware.board.vo.DepartBoard;
import com.kitware.board.vo.NoticeBoard;
import com.kitware.board.vo.PhotoBoard;
import com.kitware.member.vo.Members;
import com.oreilly.servlet.MultipartRequest;

public class BoardWriteController implements Controller {
	private BoardService service;
	private String saveFileName;
	private String originFileName;
	private String forwardURL;

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
		String mode = request.getParameter("mode");
		System.out.println("mode!!!!!!!!!!!!!"+mode);
		// 현재 컨텍스트의 톰캣 절대경로를 반환한다.
		String contextPath = request.getSession().getServletContext().getRealPath("/");
		String saveDirectory = "D:\\apache-tomcat-8.5.30\\webapps\\upload";

		request.setCharacterEncoding("UTF-8");
		// 로그인 세션값 가져오기.
		HttpSession session = request.getSession();
		Members loginInfo = (Members) session.getAttribute("loginInfo");
		String emp_num = loginInfo.getEmp_num();
		String name = loginInfo.getName();
		String dept_num = loginInfo.getDept_num();

		NoticeBoard noticeBoard = new NoticeBoard();
		PhotoBoard photoBoard = new PhotoBoard();
		DepartBoard deptBoard = new DepartBoard();
		// 파일첨부
		MultipartRequest mr;
		int maxPostSize = 1024 * 2000000;
		String encoding = "UTF-8";
		// 객체가 생성됨과 동시에 파일업로드가 이루어짐.
		mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, new MyRenamePolicy());
		String flag = mr.getParameter("flag"); // flag값을 이용해서 1일 때는 이미지를 처리하도록.
		File file1 = mr.getFile("file1"); // 저장된 파일
		if (file1 != null) {
			saveFileName = file1.getName();
			int indexExt = saveFileName.lastIndexOf(".");
			int index_ = saveFileName.lastIndexOf("_");
			String ext = saveFileName.substring(indexExt); // .txt , .jpg 확장자
			String fileName = saveFileName.substring(0, index_); // 확장자 없는 원본 파일명
			originFileName = fileName + ext;
			if ("1".equals(flag)) {
				// 썸네일 처리 부분.
				int thumbnail_width = 600;
				int thumbnail_height = 400;
				String upfolder = saveDirectory + "\\thumbnail";
				File SrcImgFile = new File(saveDirectory + File.separator + mr.getFilesystemName("file1"));
				BufferedImage srcImg = ImageIO.read(SrcImgFile);
				BufferedImage thumbImg;
				thumbImg = new BufferedImage(thumbnail_width, thumbnail_height, BufferedImage.TYPE_3BYTE_BGR);
				java.awt.Graphics2D g = thumbImg.createGraphics();
				g.drawImage(srcImg, 0, 0, thumbnail_width, thumbnail_height, null);
				File outFile = new File(upfolder + File.separator + mr.getFilesystemName("file1"));
				ImageIO.write(thumbImg, "PNG", outFile);

				photoBoard.setOriginFileName(originFileName);
				photoBoard.setSaveFileName(saveFileName);
				photoBoard.setPath(saveDirectory + "\\" + saveFileName);
			} else if ("dept".equals(mode)) {
				deptBoard.setOriginFileName(originFileName);
				deptBoard.setSaveFileName(saveFileName);
				deptBoard.setPath(saveDirectory + "\\" + saveFileName);
			} else {
				noticeBoard.setOriginFileName(originFileName);
				noticeBoard.setSaveFileName(saveFileName);
				noticeBoard.setPath(saveDirectory + "\\" + saveFileName);
			}
		}

		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		if ("1".equals(flag)) {
			photoBoard.setEmp_num(emp_num);
			photoBoard.setName(name);
			photoBoard.setTitle(title);
			photoBoard.setContent(content);
		} else if ("dept".equals(mode)) {
			deptBoard.setEmp_num(emp_num);
			deptBoard.setName(name);
			deptBoard.setDept_num(Integer.parseInt(dept_num));
			deptBoard.setTitle(title);
			deptBoard.setContent(content);
		} else {
			noticeBoard.setEmp_num(emp_num);
			noticeBoard.setName(name);
			noticeBoard.setTitle(title);
			noticeBoard.setContent(content);
		}

		try {
			if (flag.equals("1")) {
				service.insertPhotoBoard(photoBoard);
			} else if ("dept".equals(mode)) {
				service.insertDeptBoard(deptBoard);
			} else {
				service.insertNoticeBoard(noticeBoard);
			}
			request.setAttribute("result", "1");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "-1"); // 시스템상 에러
		}

		forwardURL = "/board/writeresult.jsp";

		return forwardURL;
	}

}
