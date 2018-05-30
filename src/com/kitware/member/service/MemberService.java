package com.kitware.member.service;

import java.util.List;

import com.kitware.member.dao.MemberSelectDAO;
import com.kitware.member.dao.MemberSelectDAOOracle;
import com.kitware.member.vo.DeptInfo;
import com.kitware.member.vo.GradeInfo;
import com.kitware.member.vo.Members;

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
	
	public List<Members> getAllmembers() throws Exception {
		return dao.selectAllmembers();
	}

	public List<Members> getGrantorInfo(String position_num, String dept_num) throws Exception{
		return dao.getGradeMember(position_num, dept_num);
	}

	public List<DeptInfo> getDeptInfo() throws Exception {
		return dao.getDepartments();
	}

	public List<GradeInfo> getGradeInfo() throws Exception {
		return dao.getGradeInfo();
	}
	
}
