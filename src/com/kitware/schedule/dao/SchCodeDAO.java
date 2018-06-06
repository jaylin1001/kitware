package com.kitware.schedule.dao;

import java.util.List;

import com.kitware.schedule.vo.Schedule;

public interface SchCodeDAO {
	//오버라이딩 될 추상메소드 적기
	
	//로그인한 사원의 개인 일정 가져오기 [개인일정]
	List<Schedule> selectPersonal(String id) throws Exception;
	
	//로그인한 사원이 속한 부서 일정 가져오기   [부서일정]
	List<Schedule> selectDepartment(String id) throws Exception;
	
	//회사의 전체 공통일정 가져오기   [회사일정]
	List<Schedule> selectCompany (String id) throws Exception;
	
	//개인,부서,회사 일정을 한번에 볼 수 있는  [전체일정]
	List<Schedule> selectTotal(String id) throws Exception;
	
	List<Schedule> selectPersonalToday(String id, String date) throws Exception;
}
