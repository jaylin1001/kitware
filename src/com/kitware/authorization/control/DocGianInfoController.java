package com.kitware.authorization.control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.A.control.Controller;
import com.kitware.authorization.service.DocManipulService;
import com.kitware.member.vo.DeptInfo;

public class DocGianInfoController implements Controller {
	// Calendar cal = Calendar.getInstance();
	DocManipulService service = DocManipulService.getInstance();

	public DocGianInfoController() {
		super();
	}

	public DocGianInfoController(DocManipulService service) {
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
		String kind = request.getParameter("kind");
		String forwardURL = null;

		try {
			String doc_num = service.getDocNum();
			SimpleDateFormat dformat = new SimpleDateFormat("yyMM");
			String year = dformat.format(new Date());
			List<DeptInfo> list;
			int sub = 0;
			if (doc_num != null)
				sub = doc_num.indexOf("-");
			list = service.getDeptList();
			if (doc_num == null || !doc_num.substring(0, sub).equals(year)) {
				doc_num = year + "-0001";
			} else {
				doc_num = doc_num.substring(0, sub) + "-"
						+ String.format("%04d%n", (Integer.parseInt(doc_num.substring(sub + 1)) + 1));
			}
			request.setAttribute("doc_num", doc_num);
			request.setAttribute("list", list);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (kind.equals("gian")) {
			forwardURL = "authorization/docwrite.jsp";
		} else if (kind.equals("balju")) {
			forwardURL = "authorization/baljuwrite.jsp";
		} else if (kind.equals("chuljang")) {// 구현안됨
			forwardURL = "authorization/chuljangwrite.jsp";
		} else if (kind.equals("jotae")) {// 구현안됨
			forwardURL = "authorization/jotaewrite.jsp";
		} else {
		}
		return forwardURL;
	}
}