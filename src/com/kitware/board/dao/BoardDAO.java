package com.kitware.board.dao;

import java.util.List;

import com.kitware.board.vo.NoticeBoard;

public interface BoardDAO {
	//오버라이딩 될 추상메소드 적기
	
	//공지 게시판에 해당하는 정보 SELECT
	List<NoticeBoard> selectNoticeBoard (int page) throws Exception;
	//총 게시물수를 구해온다.
	int selectCount() throws Exception;
}
