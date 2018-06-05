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
import com.kitware.board.vo.NoticeBoard;
import com.kitware.board.vo.PhotoBoard;
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
	private String flag; //이미지 게시판 파일 처리를 위한 flag 변수

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
		String delflag = request.getParameter("delflag");
		
		NoticeBoard noticeBoard = new NoticeBoard();
		PhotoBoard photoBoard = new PhotoBoard();
		
		int maxPostSize = 1024 * 2000000;
		String encoding = "UTF-8";

		// 객체가 생성됨과 동시에 파일업로드가 이루어짐.
		if(delseq == null) {
			// 파일첨부
			MultipartRequest mr;
			mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, new MyRenamePolicy());
			file1 = mr.getFile("file1");
			flag = mr.getParameter("flag");
			if(file1 != null) {//새롭게 변경할 파일이 있다면
				saveFileName = file1.getName();
				int indexExt = saveFileName.lastIndexOf(".");
				int index_ = saveFileName.lastIndexOf("_");
				String ext = saveFileName.substring(indexExt); // .txt , .jpg  확장자
				String fileName = saveFileName.substring(0, index_); // 확장자 없는 원본 파일명
				originFileName = fileName+ext;
				
				if(flag.equals("1")) {//이미지 게시판 수정이 들어왔을 때만 썸네일 처리를 하면 된다.
					//썸네일 처리 부분.
					int thumbnail_width = 600;
					int thumbnail_height = 400;
					String upfolder = saveDirectory+"\\thumbnail";
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
					photoBoard.setPath(saveDirectory+"\\"+saveFileName);
				}else {
					noticeBoard.setOriginFileName(originFileName);
					noticeBoard.setSaveFileName(saveFileName);
					noticeBoard.setPath(saveDirectory+"\\"+saveFileName);	
				}
			}
			
			hitseq = mr.getParameter("hitseq");
			seq = mr.getParameter("seq");
			title = mr.getParameter("title");
			content = mr.getParameter("content");
			if(flag != null) {
				photoBoard.setSeq(seq);
				photoBoard.setTitle(title);
				photoBoard.setContent(content);
			}else {
				noticeBoard.setSeq(seq);
				noticeBoard.setTitle(title);
				noticeBoard.setContent(content);
			}
		}
		
		
		try {
			if (delseq != null) {
				if(delflag.equals("1")) {
					service.deletePhotoBoard(delseq);
				}else {
					service.deleteNoticeBoard(delseq);
				}
			}else {
				if(file1 == null){ //새로 등록한 파일이 없을 때는 제목,내용만 수정
					if(flag != null) {
						service.updatePhotoBoard(photoBoard);
					}else {
						service.updateNoticeBoard(noticeBoard);
					}
				}else {// 새로 등록한 파일이 존재할 때는 제목,내용,파일까지 전부 수정.
					if(flag != null) {
						service.updatePhotoBoardFile(photoBoard);
					}else {
						service.updateNoticeBoardFile(noticeBoard);
					}
				}
			}
			
			if (hitseq != null) {
				if(flag != null) {
					service.updatePBHit(hitseq);
				}else {
					service.updateHit(hitseq);
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
