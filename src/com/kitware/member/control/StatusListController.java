package com.kitware.member.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.A.control.Controller;
import com.kitware.member.service.MemberService;
import com.kitware.member.vo.PageBean;
import com.kitware.member.vo.StatusBoard;

public class StatusListController implements Controller {
	private MemberService service;
	String forwardURL;
	int totalCount;

	public StatusListController() {
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
		String page = request.getParameter("page");
		String category = request.getParameter("category");
		System.out.println("category::::::::::"+category);
		String enumsearch = request.getParameter("enumsearch");
		String deptsearch = request.getParameter("deptsearch");
		System.out.println(deptsearch);
		String deptsearch2 = "";
		if (deptsearch != null) {
			if (deptsearch.equals("1")) {
				deptsearch2 = "100";
			} else if (deptsearch.equals("2")) {
				deptsearch2 = "200";
			} else if (deptsearch.equals("3")) {
				deptsearch2 = "300";
			} else if (deptsearch.equals("4")) {
				deptsearch2 = "400";
			} else if (deptsearch.equals("5")) {
				deptsearch2 = "500";
			}
		}

		String grsearch = request.getParameter("grsearch");
		String grsearch2 = "";
		if (grsearch != null) {
			if (grsearch.equals("1")) {
				grsearch2 = "10";
			} else if (grsearch.equals("2")) {
				grsearch2 = "20";
			} else if (grsearch.equals("3")) {
				grsearch2 = "30";
			} else if (grsearch.equals("4")) {
				grsearch2 = "40";
			} else if (grsearch.equals("5")) {
				grsearch2 = "50";
			} else if (grsearch.equals("6")) {
				grsearch2 = "60";
			}
		}
		String idsearch = request.getParameter("idsearch");
		String namesearch = request.getParameter("namesearch");
		System.out.println("id::::::::::"+idsearch);
		List<StatusBoard> list = new ArrayList<>();

		int intPage = 1;
		int flag = 0;
		if (page != null) {
			System.out.println("page:::::::"+page);
			intPage = Integer.parseInt(page);
			flag = 1;
		}
		try {
			totalCount = service.findCount();
			int totalPage = 0;
			int cntPerPage = 10;
			totalPage = (int) Math.ceil((double) totalCount / cntPerPage);
			int cntPerPageGroup = 5;
			int startPage = (int) Math.floor((double) (intPage) / (cntPerPageGroup + 1)) * cntPerPageGroup + 1;
			int endPage = startPage + cntPerPageGroup - 1;
			if (endPage > totalPage) {
				endPage = totalPage;
			}
			
			if (category != null) {
				if (category.equals("1")) {
					System.out.println("111111111111111");
					list = service.findByEmpnum(intPage, enumsearch);
				} else if (category.equals("2")) {
					list = service.findByDept(intPage, deptsearch2);
				} else if (category.equals("3")) {
					list = service.findByGrade(intPage, grsearch2);
				} else if (category.equals("4")) {
					list = service.findById(intPage, idsearch);
				} else if (category.equals("5")) {
					list = service.findByName(intPage, namesearch);
				} else if(category.equals("0")) {
					list = service.findAll(intPage);
				}
			}else {
				list = service.findAll(intPage);
			}
			PageBean<StatusBoard> pb = new PageBean<>();
			System.out.println("controllerlist" + list);
			pb.setCurrentPage(intPage);// 현재페이지
			pb.setTotalPage(totalPage); // 총페이지
			pb.setList(list); // 목록
			pb.setStartPage(startPage); // 시작페이지
			pb.setEndPage(endPage); // 끝페이지
			pb.setTotalCount(totalCount); // 총 게시글 갯수
			pb.setCntPerPage(cntPerPage);
			request.setAttribute("pagebean", pb);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", e.getMessage());
		}
		if (flag == 0) {
			forwardURL = "/modmember/memberstatus.jsp";
		} else {
			forwardURL = "/modmember/memberstatusresult.jsp";
		}
		return forwardURL;
	}

}
