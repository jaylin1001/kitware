package com.kitware.board.control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.A.control.Controller;
import com.kitware.board.service.BoardService;
import com.kitware.board.vo.DepartBoard;
import com.kitware.board.vo.NoticeBoard;
import com.kitware.board.vo.PhotoBoard;
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
	private String flag; // 이미지 게시판 조회수를 위한 flag 변수
	private String editflag; // 이미지 게시판 정보수정을 위한 editflag 변수

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
		String saveDirectory = "E:\\apache-tomcat-8.5.30-windows-x64\\apache-tomcat-8.5.30\\webapps\\upload";
		String delseq = request.getParameter("delseq");
		String delflag = request.getParameter("delflag");
		String mode = request.getParameter("mode");
		System.out.println("모드입니다~~~~~~~~"+mode);
		hitseq = request.getParameter("hitseq");
		flag = request.getParameter("flag");// 조회수를 위한 flag
		if (flag == null) {
			flag = "0"; // 0이라는건 공지게시판 1은 이미지게시판
		}
		System.out.println("flag 지금 몇임?" + flag);
		System.out.println("hitseq 몇임:" + hitseq);
		NoticeBoard noticeBoard = new NoticeBoard();
		PhotoBoard photoBoard = new PhotoBoard();
		DepartBoard deptBoard = new DepartBoard();

		int maxPostSize = 1024 * 2000000;
		String encoding = "UTF-8";

		// 삭제,조회수 증가가 아닐때만 실행될 곳
		if (delseq == null && hitseq == null) {
			// 파일첨부
			MultipartRequest mr;
			// 객체가 생성됨과 동시에 파일업로드가 이루어짐.
			mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, new MyRenamePolicy());
			file1 = mr.getFile("file1");
			editflag = mr.getParameter("editflag"); // 정보 및 파일 처리를 위한 flag
			if (file1 != null) {// 새롭게 변경할 파일이 있다면
				saveFileName = file1.getName();
				int indexExt = saveFileName.lastIndexOf(".");
				int index_ = saveFileName.lastIndexOf("_");
				String ext = saveFileName.substring(indexExt); // .txt , .jpg 확장자
				String fileName = saveFileName.substring(0, index_); // 확장자 없는 원본 파일명
				originFileName = fileName + ext;

				if ("1".equals(editflag)) {// 이미지 게시판 수정이 들어왔을 때만 썸네일 처리를 하면 된다.
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
			seq = mr.getParameter("seq");
			title = mr.getParameter("title");
			content = mr.getParameter("content");

			if ("1".equals(editflag)) {
				photoBoard.setSeq(seq);
				photoBoard.setTitle(title);
				photoBoard.setContent(content);
			} else if ("dept".equals(mode)) {
				deptBoard.setSeq(Integer.parseInt(seq));
				deptBoard.setTitle(title);
				deptBoard.setContent(content);
			} else {
				noticeBoard.setSeq(seq);
				noticeBoard.setTitle(title);
				noticeBoard.setContent(content);
			}
		}

		try {

			if (delseq != null) {
				if ("1".equals(delflag)) {
					service.deletePhotoBoard(delseq);
				} else if ("dept".equals(mode)) {
					service.deleteDeptBoard(Integer.parseInt(delseq));
				} else {
					service.deleteNoticeBoard(delseq);
				}
			} else {
				if (hitseq != null) {

					if ("1".equals(flag)) {
						service.updatePBHit(hitseq);
					} else if ("dept".equals(mode)) {
						service.updateDeptHit(hitseq);
					} else {
						service.updateHit(hitseq);
					}
				} else {
					if (file1 == null) { // 새로 등록한 파일이 없을 때는 제목,내용만 수정
						if ("1".equals(editflag)) {
							service.updatePhotoBoard(photoBoard);
						} else if ("dept".equals(mode)) {
							service.updateDeptBoard(deptBoard);
						} else {
							service.updateNoticeBoard(noticeBoard);
						}
					} else {// 새로 등록한 파일이 존재할 때는 제목,내용,파일까지 전부 수정.
						if ("1".equals(editflag)) {
							service.updatePhotoBoardFile(photoBoard);
						} else if ("dept".equals(mode)) {
							service.updateDeptBoardFile(deptBoard);
						} else {
							service.updateNoticeBoardFile(noticeBoard);
						}
					}
				}
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
