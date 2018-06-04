package com.kitware.member.dao;

import java.util.List;

import com.kitware.member.vo.DeptInfo;
import com.kitware.member.vo.GradeInfo;
import com.kitware.member.vo.Mail;
import com.kitware.member.vo.Members;

public interface MemberSelectDAO {
	//로그인한 사원의 정보 가져오기
	public Members selectMemberInfo(String id) throws Exception;
	public List<Members> selectAllmembers() throws Exception;
	public List<DeptInfo> getDepartments() throws Exception;
	public List<GradeInfo> getGradeInfo() throws Exception;
	public List<Members> getGradeMember(String position_num, String dept_num) throws Exception;
	//메일(쪽지) 파트
	public List<Mail> selectMailList(String emp_num) throws Exception;//리스트
	public Mail selectMailAll(String emp_num) throws Exception;//상세보기
	public void insertMail(Mail mail) throws Exception;
	public void updateEdit(Mail mail, String mail_num) throws Exception;
	public void updateWatch(String watch_yn) throws Exception;
	
}
