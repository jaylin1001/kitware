package com.kitware.member.dao;

import java.util.List;

import com.kitware.member.vo.Members;
import com.kitware.member.vo.MembersDetailInfo;
import com.kitware.member.vo.StatusBoard;

public interface MemberSelectDAO {
	//로그인한 사원의 정보 가져오기
	Members selectMemberInfo(String id) throws Exception;
		
	void insertMembers(Members members) throws Exception;
	void insertMembers(MembersDetailInfo mdetail) throws Exception;
	List<StatusBoard> selectStatusBoard(int page) throws Exception;
	int selectCount() throws Exception;
}
