package com.kitware.board.dao;

import java.util.List;

import com.kitware.board.vo.NoticeBoard;
import com.kitware.board.vo.PhotoBoard;

public interface BoardDAO {
	//오버라이딩 될 추상메소드 적기
	
	//공지 게시판에 해당하는 정보 SELECT
	List<NoticeBoard> selectNoticeBoard (int page) throws Exception;
	//총 게시물수를 구해온다.
	int selectCount() throws Exception;
	//게시물 글쓰기
	void insertNoticeBoard(NoticeBoard noticeBoard) throws Exception;
	//글 수정시 파일 변화 없는 게시물 업데이트
	void updateNoticeBoard(NoticeBoard noticeBoard) throws Exception;
	//게시물 삭제
	void deleteNoticeBoard(String seq) throws Exception;
	//조회수를 1씩 증가
	void updateHit(String hitseq) throws Exception;
	//글 수정시 파일 변화 있는 게시물 업데이트
	void updateNoticeBoardFile(NoticeBoard noticeBoard) throws Exception;
	//이전글 가져오기
	NoticeBoard selectPrePost(String seq) throws Exception;
	//다음글 가져오기
	NoticeBoard selectNextPost(String seq) throws Exception;
	//사진 게시판 해당하는 정보 SELECT
	List<PhotoBoard> selectPhotoBoard(int page) throws Exception;
	//사진 게시판 글 추가
	void insertPhotoBoard(PhotoBoard photoBoard) throws Exception;
	//사진 게시판 총 게시물 수 구하기
	int selectPBCount() throws Exception;
	//사진게시판 조회수 up
	void updatePBHit(String hitseq) throws Exception;
	//사진게시판 제목,내용만 수정
	void updatePhotoBoard(PhotoBoard photoBoard)throws Exception;
	//사진게시판 첨부파일까지 수정
	void updatePhotoBoardFile(PhotoBoard photoBoard)throws Exception;
	//사진게시판 게시글 삭제
	void deletePhotoBoard(String delseq)throws Exception;
	//사진게시판 이전글 가져와라
	PhotoBoard selectPBPrePost(String seq)throws Exception;
	//사진게시판 다음글 가져와라
	PhotoBoard selectPBNextPost(String seq) throws Exception;
	
}
