package com.kitware.member.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.A.control.Controller;
import com.kitware.member.service.MemberService;
import com.kitware.member.vo.Mail;
import com.kitware.member.vo.Members;

public class MailController implements Controller{
	MemberService service;

	public MemberService getService() {
		return service;
	}

	public void setService(MemberService service) {
		this.service = service;
	}

	public MailController() {
		super();
	}

	public MailController(MemberService service) {
		super();
		this.service = service;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String forwardURL = null;
		Members loginInfo = (Members) session.getAttribute("loginInfo");
		String emp_num = loginInfo.getEmp_num();
		Mail mails =null;
		String mail_num = request.getParameter("mail_num");
		String mode = request.getParameter("mode");
		
		String mail_content = request.getParameter("mail_content");
		String mail_title = request.getParameter("mail_title");
		System.out.println("로그인번호" + emp_num);
		System.out.println("문서번호" + mail_num);
		System.out.println(mode);

		try {
			if(mode.equals("editread")) {
				mails = service.selectMailAll(emp_num);
				forwardURL = "/Mail/mailEdit.jsp";
			}
			else if(mode.equals("update")) {
				Mail mail = new Mail();
				mail.setMail_content(mail_content);
				mail.setMail_title(mail_title);
				service.updateEdit(mail, mail_num);
				request.setAttribute("result", "1");
				forwardURL = "/Mail/editresult.jsp";
			}else if(mode.equals("read")) {
				mails = service.selectMailAll(emp_num);
				forwardURL = "/Mail/mailRead.jsp";
			}
			
			request.setAttribute("Mailcont", mails);
		} catch (Exception e) {
			request.setAttribute("result", e.getMessage());
			e.printStackTrace();
		}
		
		return forwardURL;
	}
}