package com.kitware.member.service;

import java.util.List;

import com.kitware.member.dao.MemberSelectDAO;
import com.kitware.member.dao.MemberSelectDAOOracle;
import com.kitware.member.vo.DeptInfo;
import com.kitware.member.vo.GradeInfo;
import com.kitware.member.vo.Mail;
import com.kitware.member.vo.Members;
import com.kitware.member.vo.MembersDetailInfo;
import com.kitware.member.vo.StatusBoard;
import com.kitware.member.vo.StatusDetailBoard;

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
	public List<Mail> selectMailList(String emp_num) throws Exception{
		return dao.selectMailList(emp_num);
	}//리스트
	public List<Mail> selectMailList2(String emp_num) throws Exception{
		return dao.selectMailList2(emp_num);
	}//리스트
	public Mail selectMailList3(String emp_num) throws Exception{
		return dao.selectMailList3(emp_num);
	}//리스트
	public Mail selectMailAll(String emp_num) throws Exception{
		return dao.selectMailAll(emp_num);
	}//상세보기
	public void updateEdit(Mail mail, String mail_num) throws Exception{
		dao.updateEdit(mail, mail_num);
	}
	public void insertMail(Mail mail) throws Exception{
		dao.insertMail(mail);
	}
	public void updateWatch(String mail_num) throws Exception{
		dao.updateWatch(mail_num);
	}
		
	public void modmemberDetail(MembersDetailInfo mdetail) throws Exception{
		dao.insertMembers(mdetail);		
	}		
	public int findCount() throws Exception{
		return dao.selectCount();
	}
	public StatusDetailBoard findDetail(int emp_num) throws Exception{
			return dao.selectStatusBoardDetail(emp_num) ;
	}
	public void correctMembers(Members members) throws Exception {
		dao.CorrectMembers(members);		
	}
	public void correctMemberDetail(MembersDetailInfo mdetail) throws Exception {
		dao.CorrectMembers(mdetail);		
	}
	public void deleteMembers(String emp_num) throws Exception {
		dao.deleteMembers(emp_num);		
	}	
	public List<StatusBoard> findAll(int page) throws Exception{
		return dao.selectStatusBoard(page);
	}
	public List<StatusBoard> findByEmpnum(int page, String enumsearch) throws Exception {
		return dao.selecEnum(page, enumsearch );
	}
	public List<StatusBoard> findByDept(int page, String deptsearch2) throws Exception {
		return dao.selectDept(page, deptsearch2);
	}
	public List<StatusBoard> findByGrade(int page, String grsearch2) throws Exception {
		return dao.selectGrade(page, grsearch2);
	}
	public List<StatusBoard> findById(int page, String idsearch) throws Exception {
		return dao.selectId(page, idsearch);
	}
	public List<StatusBoard> findByName(int page, String namesearch) throws Exception {
		return dao.selectName(page, namesearch);
	}
	public Members findMaxEmum() throws Exception{
		return dao.selectMaxEnum();
	}
	public String idCheck(String idValue) throws Exception{	
			String id =  dao.idCheck(idValue);			
			if(id==null) {
				return "1"; //아이디가 없는경우
			}else {
				return "-1";
			}		
	}
}
