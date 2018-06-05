package com.kitware.member.dao;

import java.util.List;


import com.kitware.member.vo.DeptInfo;
import com.kitware.member.vo.GradeInfo;
import com.kitware.member.vo.Mail;
import com.kitware.member.vo.Members;
import com.kitware.member.vo.MembersDetailInfo;
import com.kitware.member.vo.StatusBoard;
import com.kitware.member.vo.StatusDetailBoard;

public interface MemberSelectDAO {
	//로그인한 사원의 정보 가져오기
	public Members selectMemberInfo(String id) throws Exception;
	public List<Members> selectAllmembers() throws Exception;
	public List<DeptInfo> getDepartments() throws Exception;
	public List<GradeInfo> getGradeInfo() throws Exception;
	public List<Members> getGradeMember(String position_num, String dept_num) throws Exception;
	//메일(쪽지) 파트
	public List<Mail> selectMailList(String emp_num) throws Exception;//리스트
	public List<Mail> selectMailList2(String emp_num) throws Exception;//리스트
	public Mail selectMailList3(String emp_num) throws Exception;//안읽은거 리스트
	public Mail selectMailAll(String emp_num) throws Exception;//상세보기
	public void insertMail(Mail mail) throws Exception;
	public void updateEdit(Mail mail, String mail_num) throws Exception;
	public void updateWatch(String mail_num) throws Exception;
	public void insertMembers(Members members) throws Exception;
	public void insertMembers(MembersDetailInfo mdetail) throws Exception;
	public int selectCount() throws Exception;
	public StatusDetailBoard selectStatusBoardDetail(int emp_num) throws Exception;
	public void CorrectMembers(Members members) throws Exception;
	public void CorrectMembers(MembersDetailInfo mdetail) throws Exception;	
	public void deleteMembers(String emp_num) throws Exception;
	public List<StatusBoard> selectStatusBoard(int page) throws Exception;
	public List<StatusBoard> selecEnum(int page, String enumsearch) throws Exception;
	public List<StatusBoard> selectDept(int page, String deptsearch2) throws Exception;
	public List<StatusBoard> selectGrade(int page, String grsearch2) throws Exception;
	public List<StatusBoard> selectId(int page, String idsearch) throws Exception;
	public List<StatusBoard> selectName(int page, String namesearch) throws Exception;
	public Members selectMaxEnum() throws Exception;
	public String idCheck(String idValue) throws Exception;
	}
