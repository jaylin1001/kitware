package com.kitware.member.dao;

import com.kitware.member.vo.Members;

public interface MemberSelectDAO {
	//로그인한 사원의 정보 가져오기
	Members selectMemberInfo(String id) throws Exception;
}
