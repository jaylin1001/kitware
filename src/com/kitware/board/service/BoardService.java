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
}
