package com.kitware.member.dao;

import java.util.List;

import com.kitware.member.vo.DeptInfo;
import com.kitware.member.vo.GradeInfo;
import com.kitware.member.vo.Members;

public interface MemberSelectDAO {
	//로그인한 사원의 정보 가져오기
	Members selectMemberInfo(String id) throws Exception;
	List<Members> selectAllmembers() throws Exception;
	List<DeptInfo> getDepartments() throws Exception;
	List<GradeInfo> getGradeInfo() throws Exception;
	List<Members> getGradeMember(String position_num, String dept_num) throws Exception;
}
