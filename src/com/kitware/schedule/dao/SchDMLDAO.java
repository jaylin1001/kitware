package com.kitware.schedule.dao;

import java.util.List;

import com.kitware.schedule.vo.Schedule;

public interface SchDMLDAO {
	//오버라이딩 될 추상메소드 적기
	
	//로그인한 사원의  일정 추가
	void insertSchedule(Schedule schedule) throws Exception;
	
}
