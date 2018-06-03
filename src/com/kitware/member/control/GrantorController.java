package com.kitware.member.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.A.control.Controller;
import com.kitware.member.service.MemberService;
import com.kitware.member.vo.DeptInfo;
import com.kitware.member.vo.GradeInfo;
import com.kitware.member.vo.Members;

public class GrantorController implements Controller {
	private MemberService service = MemberService.getInstance();

	public GrantorController() {
		// TODO Auto-generated constructor stub
	}

	public GrantorController(MemberService service) {
		super();
		this.service = service;
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
		try {
			System.out.println(request.getContextPath());
			List<DeptInfo> deptlist = service.getDeptInfo();
			List<GradeInfo> gradelist = service.getGradeInfo();
			List<Members> memberl = service.getAllmembers();

			List<List<List<Members>>> memberlist2 = new ArrayList<>();

			List<Members> list = new ArrayList<>();

			// for (DeptInfo dept : deptlist) {
			// String dnum = dept.getDept_num();
			// for (GradeInfo grade : gradelist) {
			// String pnum = grade.getPosition_num();
			// for (Members member : memberl) {
			// if (member.getDept_num().equals(dept.getDept_num())
			// && member.getPosition_num().equals(grade.getPosition_num())) {
			//
			// }
			// }
			// }
			// }
			for (DeptInfo dept : deptlist) {
				List<List<Members>> memberlist = new ArrayList<>();
				for (GradeInfo grade : gradelist) {

					memberlist.add(service.getGrantorInfo(grade.getPosition_num(), dept.getDept_num()));
				}
				memberlist2.add(memberlist);
			}
			System.out.println(deptlist.size() + "::" + gradelist.size() + "::" + memberlist2.size());

			request.setAttribute("deptlist", deptlist);
			request.setAttribute("gradelist", gradelist);
			request.setAttribute("memberlist", memberlist2);

			// request.setAttribute("id", id);

		} catch (

		Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id = request.getParameter("id");
		System.out.println(id);
		String forwardURL = "/authorization/addgrantor.jsp?id=" + id;
		return forwardURL;
	}
}
