package com.kitware.member.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.A.control.Controller;
import com.kitware.authorization.service.DocManipulService;
import com.kitware.member.service.MemberService;
import com.kitware.member.vo.DeptInfo;
import com.kitware.member.vo.GradeInfo;
import com.kitware.member.vo.Mail;
import com.kitware.member.vo.Members;

public class MailWriteController implements Controller {
	DocManipulService service;
	MemberService mservice;

	public DocManipulService getService() {
		return service;
	}

	public void setService(DocManipulService service) {
		this.service = service;
	}

	public MemberService getMservice() {
		return mservice;
	}

	public void setMservice(MemberService mservice) {
		this.mservice = mservice;
	}

	public MailWriteController() {
		super();
	}

	public MailWriteController(MemberService mservice) {
		super();
		this.mservice = mservice;
	}

	public MailWriteController(DocManipulService service) {
		super();
		this.service = service;
	}

	public MailWriteController(DocManipulService service, MemberService mservice) {
		super();
		this.service = service;
		this.mservice = mservice;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Members member = (Members) session.getAttribute("loginInfo");
		String forwardURL = null;
		String emp_num = member.getEmp_num();
		String mode = request.getParameter("mode");
		System.out.println(mode);
		String replace = request.getParameter("replace");
		System.out.println("받는이"+replace);
		
		try {
			List<DeptInfo> deptlist = service.getDeptList();
			List<GradeInfo> gradelist = mservice.getGradeInfo();
			List<Members> memberlist = mservice.getAllmembers();
			request.setAttribute("deptlist", deptlist);
			request.setAttribute("gradelist", gradelist);
			request.setAttribute("memberlist", memberlist);
			if(mode.equals("writeview")) {
				forwardURL = "/Mail/mailWrite.jsp";
			}else if(mode.equals("write")) {
				String mail_title = request.getParameter("mail_title");
				String mail_content = request.getParameter("mail_content");
				Mail mail = new Mail();
				mail.setEmp_num(emp_num);
				mail.setRcv_num(replace);
				mail.setMail_title(mail_title);
				mail.setMail_content(mail_content);
				mservice.insertMail(mail);
				request.setAttribute("result", 1);
				forwardURL = "/Mail/writeresult.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("result", -1);
			forwardURL = "/Mail/writeresult.jsp";
			e.printStackTrace();
		}
		
		return forwardURL;
	}

}
