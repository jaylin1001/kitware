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
<<<<<<< HEAD
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
=======
>>>>>>> yunjey
}
