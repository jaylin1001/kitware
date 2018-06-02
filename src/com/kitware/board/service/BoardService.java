package com.kitware.board.service;

import java.util.List;

import com.kitware.board.dao.BoardDAO;
import com.kitware.board.dao.BoardDAOOracle;
import com.kitware.board.vo.NoticeBoard;


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
		System.out.println("여기까지도 왔니 여기는 파일변화없는 service야...");
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
}
