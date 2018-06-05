package com.kitware.member.dao;

import java.util.List;

import com.kitware.member.vo.Members;
import com.kitware.member.vo.MembersDetailInfo;
import com.kitware.member.vo.StatusBoard;
import com.kitware.member.vo.StatusDetailBoard;

public interface MemberSelectDAO {
	//로그인한 사원의 정보 가져오기
	Members selectMemberInfo(String id) throws Exception;
		
	void insertMembers(Members members) throws Exception;
	void insertMembers(MembersDetailInfo mdetail) throws Exception;	
	int selectCount() throws Exception;
	StatusDetailBoard selectStatusBoardDetail(int emp_num) throws Exception;
	void CorrectMembers(Members members) throws Exception;
	void CorrectMembers(MembersDetailInfo mdetail) throws Exception;	
	void deleteMembers(String emp_num) throws Exception;
	List<StatusBoard> selectStatusBoard(int page) throws Exception;
	List<StatusBoard> selecEnum(int page, String enumsearch) throws Exception;
	List<StatusBoard> selectDept(int page, String deptsearch2) throws Exception;
	List<StatusBoard> selectGrade(int page, String grsearch2) throws Exception;
	List<StatusBoard> selectId(int page, String idsearch) throws Exception;
	List<StatusBoard> selectName(int page, String namesearch) throws Exception;
	Members selectMaxEnum() throws Exception;
	Members idCheck(String idValue) throws Exception;	
}
