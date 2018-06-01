package com.kitware.authorization.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.A.control.Controller;
import com.kitware.authorization.service.DocManipulService;
import com.kitware.member.vo.Members;

public class DocGJUpdateController implements Controller {
	DocManipulService service;
	
	public DocGJUpdateController() {
		super();
	}

	public DocGJUpdateController(DocManipulService service) {
		super();
		this.service = service;
	}

	public DocManipulService getService() {
		return service;
	}

	public void setService(DocManipulService service) {
		this.service = service;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Members loginInfo = (Members)session.getAttribute("loginInfo");	
		String conf_num = loginInfo.getEmp_num();
		String forwardURL = null ;
		String doc_num = request.getParameter("doc_num");
		String mode = request.getParameter("mode");
		String smode = request.getParameter("smode");
		String kind = request.getParameter("kind");
		String conf_count = request.getParameter("count");
		String acs_yn;
		String state;
		System.out.println(doc_num);
		System.out.println(mode);
		System.out.println(kind);
		System.out.println("smode"+smode);
		
		try {
			if(kind.equals("up")) {
				//결재 승인
				if(mode.equals("110") || mode.equals("10")|| mode.equals("0") && smode.equals("1")) {
				//진행>>완료
				acs_yn ="1";
				state = "2";
				service.updateConf(doc_num, conf_num, acs_yn);
				service.updateState(doc_num, state);
				forwardURL = "/authorization/updateresult.jsp";
				request.setAttribute("result", "1");
				}else if(mode.equals("00") || mode.equals("000") && smode.equals("0")) {
				//상신>>진행,
				acs_yn ="1";
					if(conf_count.equals("1")) {
						state = "1";
					}else{
						state = "2";
					}
				service.updateConf(doc_num, conf_num, acs_yn);
				service.updateState(doc_num, state);
				forwardURL = "/authorization/updateresult.jsp";
				request.setAttribute("result", "1");
				}else{
				acs_yn ="1";
				if(conf_count.equals("2")) {
					state = "1";
				}else{
					state = "2";
				}
				service.updateConf(doc_num, conf_num, acs_yn);
				service.updateState(doc_num, state);
				forwardURL = "/authorization/updateresult.jsp";
				}
				
				
			}else if(kind.equals("down")) {
				//반려하기
				acs_yn ="0";
				state = "3";
				if(mode.equals("110") || mode.equals("10")|| mode.equals("0") && smode.equals("0")) {
					//error 나면 int로
					service.updateConf(doc_num, conf_num, acs_yn);
					service.updateState(doc_num, state);
					forwardURL = "/authorization/updateresult.jsp";
					request.setAttribute("result", "1");
					}else{
					service.updateConf(doc_num, conf_num, acs_yn);
					forwardURL = "/authorization/updateresult.jsp";
					}
			}else{
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return forwardURL;
	}

}
