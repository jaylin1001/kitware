package com.kitware.member.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.A.control.Controller;
import com.kitware.member.service.MemberService;
import com.kitware.member.vo.Members;
import com.kitware.member.vo.MembersDetailInfo;

public class ModmemberController implements Controller {
	private static final long serialVersionUID = 1L;
	private MemberService service = new MemberService();	
	public ModmemberController() {
	}

	public MemberService getService() {
		return service;
	}
	
	public void setService(MemberService service) {
		this.service = service;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
	String emp_num = request.getParameter("emp_num");
	String dept_num = request.getParameter("dept_num");
	String position_num = request.getParameter("position_num");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	String email1 = request.getParameter("email1");
	String email2 = request.getParameter("email2");
	String tel1	= request.getParameter("tel1");
	String tel2 = request.getParameter("tel2");
	String tel3 = request.getParameter("tel3");
	System.out.println(emp_num);
	Members members = new Members();
	members.setEmp_num(emp_num);
	members.setDept_num(dept_num);
	members.setPosition_num(position_num);
	members.setId(id);
	members.setPwd(pwd);
	members.setName(name);
	members.setGender(gender);
	members.setEmail1(email1);
	members.setEmail2(email2);
	members.setTel1(tel1);
	members.setTel2(tel2);
	members.setTel3(tel3);
	String birth1 = request.getParameter("birthyear");
	String birth2 = request.getParameter("birthmonth");
	String birth3 = request.getParameter("birthday");
	String hire_date1 = request.getParameter("hireyear");
	String hire_date2 = request.getParameter("hiremonth");
	String hire_date3 = request.getParameter("hireday");
	String out_date1 = request.getParameter("outyear");
	String out_date2 = request.getParameter("outmonth");
	String out_date3 = request.getParameter("outday");
	String zip1 = request.getParameter("zip1");
	String zip2 = request.getParameter("zip2");
	String addr1 = request.getParameter("addr1");
	String addr2 = request.getParameter("addr2");
	String marriage = request.getParameter("marriage");
	MembersDetailInfo mdetail = new MembersDetailInfo();
	mdetail.setEmp_num(emp_num);
	mdetail.setBirth1(birth1);
	mdetail.setBirth2(birth2);
	mdetail.setBirth3(birth3);
	mdetail.setHire_date1(hire_date1);
	mdetail.setHire_date2(hire_date2);
	mdetail.setHire_date3(hire_date3);
	mdetail.setOut_date1(out_date1);
	mdetail.setOut_date2(out_date2);
	mdetail.setOut_date3(out_date3);
	mdetail.setZip1(zip1);
	mdetail.setZip2(zip2);
	mdetail.setAddr1(addr1);
	mdetail.setAddr2(addr2);
	mdetail.setMarriage(marriage);
	try {
		service.modmembers(members);
		service.modmemberDetail(mdetail);
		request.setAttribute("result", 1);
	}catch(Exception e) {
		e.printStackTrace();
		request.setAttribute("result", -1);
	}
	String forwardURL = "modmember/modmemberresult.jsp";		
	return forwardURL;
	}

}
