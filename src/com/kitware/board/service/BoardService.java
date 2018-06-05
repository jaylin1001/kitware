package com.kitware.board.service;

import java.util.List;

import com.kitware.board.dao.BoardDAO;
import com.kitware.board.dao.BoardDAOOracle;
import com.kitware.board.vo.NoticeBoard;
import com.kitware.board.vo.PhotoBoard;


public class BoardService {
	static private BoardService service;
	private BoardDAO dao = new BoardDAOOracle();
	public BoardService() {

	}

	public static BoardService getInstance() {
		if (service == null) {
			service = new BoardService();
		}
		return service;
	}
	
	public int findCount() throws Exception{
		return dao.selectCount();
	}
	public List<NoticeBoard> findAll(int page) throws Exception{
		return dao.selectNoticeBoard(page);
	}
	public void insertNoticeBoard(NoticeBoard noticeBoard) throws Exception{
		dao.insertNoticeBoard(noticeBoard);
	}
	//글 수정 시 파일 변화 없을 때 
	public void updateNoticeBoard(NoticeBoard noticeBoard)throws Exception {
		dao.updateNoticeBoard(noticeBoard);
	}
	public void deleteNoticeBoard(String seq) throws Exception{
		dao.deleteNoticeBoard(seq);
	}
	//조회수를 1씩 증가시킨다.
	public void updateHit(String hitseq) throws Exception{
		dao.updateHit(hitseq);
	}
	//글 수정 시 파일 변화 있을 때
	public void updateNoticeBoardFile(NoticeBoard noticeBoard) throws Exception{
		dao.updateNoticeBoardFile(noticeBoard);
	}
	//이전글찾기
	public NoticeBoard findPre(String seq) throws Exception{
		return dao.selectPrePost(seq);
	}
	//다음글찾기
	public NoticeBoard findNext(String seq) throws Exception {
		return dao.selectNextPost(seq);
	}
	//사진 게시판 글 전부 찾아와라!!!
	public List<PhotoBoard> findPhotoAll(int page) throws Exception{
		return dao.selectPhotoBoard(page);
	}
	//사진게시판 글 추가
	public void insertPhotoBoard(PhotoBoard photoBoard)throws Exception {
		dao.insertPhotoBoard(photoBoard);
		
	}
	//사진게시판 총 게시글 수
	public int findPBCount() throws Exception{
		return dao.selectPBCount();
	}
	//사진게시판 조회수 up
	public void updatePBHit(String hitseq) throws Exception{
		dao.updatePBHit(hitseq);
	}
	//사진게시판 제목,내용만 수정
	public void updatePhotoBoard(PhotoBoard photoBoard) throws Exception{
		dao.updatePhotoBoard(photoBoard);
		
	}
	//사진게시판 첨부파일까지 수정
	public void updatePhotoBoardFile(PhotoBoard photoBoard)  throws Exception{
		dao.updatePhotoBoardFile(photoBoard);
	}
	//사진게시판 게시글 삭제
	public void deletePhotoBoard(String delseq) throws Exception {
		dao.deletePhotoBoard(delseq);
	}
	//사진게시판 이전글
	public PhotoBoard findPBPre(String seq) throws Exception{
		return dao.selectPBPrePost(seq);
	}
	//사진게시판 다음글
	public PhotoBoard findPBNext(String seq) throws Exception{
		return dao.selectPBNextPost(seq);
	}
}
