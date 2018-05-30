package com.kitware.member.service;

import com.kitware.member.dao.MemberSelectDAO;
import com.kitware.member.dao.MemberSelectDAOOracle;
import com.kitware.member.vo.Members;
import com.kitware.member.vo.MembersDetailInfo;

public class MemberService {
	private MemberSelectDAO dao = new MemberSelectDAOOracle();
	
	static private MemberService service;

	public MemberService() {

	}

	public static MemberService getInstance() {
		if (service == null) {
			service = new MemberService();
		}
		return service;
	}
	
	
	//로그인 확인 유무.
	public Members login(String id, String pwd) throws Exception{
		Members mb =  dao.selectMemberInfo(id);
		if(mb !=null) { //아이디가 있는 경우
			if(mb.getPwd().equals(pwd)) {//비밀번호일치
				return mb;
			}
		}
		return null;
	}	
	
	public void modmembers(Members members )  throws Exception{
		dao.insertMembers(members);
		
	}

	public void modmemberDetail(MembersDetailInfo mdetail) throws Exception{
		dao.insertMembers(mdetail);		
	}	
}