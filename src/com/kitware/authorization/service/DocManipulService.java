package com.kitware.authorization.service;

import java.util.List;

import com.kitware.authorization.dao.DocDAOOracle;
import com.kitware.authorization.vo.DocVO;
import com.kitware.member.vo.DeptInfo;

public class DocManipulService {
	static private DocManipulService service;
	DocDAOOracle dao = new DocDAOOracle();

	public DocManipulService() {

	}

	public static DocManipulService getInstance() {
		if (service == null) {
			service = new DocManipulService();
		}
		return service;
	}

	public String getDocNum() {
		return dao.getMaxDocNum();
	}

	public List<DeptInfo> getDeptList() throws Exception {
		return dao.getDeptList();
	}

	public String getEmpNum(String g1, String g1_grade) throws Exception {
		return dao.getEmpNum(g1, g1_grade);
	}

	public void insertgian(DocVO giandoc) throws Exception {
		dao.insertGianfull(giandoc);
	}
}
