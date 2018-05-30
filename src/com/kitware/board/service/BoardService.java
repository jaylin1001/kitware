package com.kitware.board.service;

public class BoardService {
	static private BoardService service;

	public BoardService() {

	}

	public static BoardService getInstance() {
		if (service == null) {
			service = new BoardService();
		}
		return service;
	}
}
