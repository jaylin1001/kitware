package com.kitware.guntae.service;

import java.util.List;

import com.kitware.authorization.vo.DocGiganVO;
import com.kitware.guntae.dao.GTDAO;
import com.kitware.guntae.dao.GTDAOOracle;
import com.kitware.guntae.vo.Gunte;
import com.kitware.guntae.vo.Yeoncha;

public class GTService {
	static private GTService service;
	private GTDAO dao = new GTDAOOracle();

	public GTService() {

	}

	public static GTService getInstance() {
		if (service == null) {
			service = new GTService();
		}
		return service;
	}

	// 근태 모든것 셀렉트
	public List<Gunte> gselectAll(String emp_num) throws Exception {
		return dao.gselectAll(emp_num);
	}

	// 근태 출석 인설트
	public void ininsert(String emp_num) throws Exception {
		dao.ininsert(emp_num);
	}

	public void outupdate(String emp_num) throws Exception {
		dao.outupdate(emp_num);
	}

	public List<DocGiganVO> dockindselectAll(String emp_num, String doc_kind) throws Exception {
		return dao.dockindselectAll(emp_num, doc_kind);
	}

	public Yeoncha selectAll(String emp_num, String years) throws Exception {
		return dao.selectAll(emp_num, years);
	}

	public void useupdate(String emp_num, String years) throws Exception {
		dao.useupdate(emp_num, years);
	}

	public List<List<String>> giganselectAll(String emp_num, String years) throws Exception {
		return dao.giganselectAll(emp_num, years);
	}

	public void giganinsert(String emp_num) throws Exception {
		dao.giganinsert(emp_num);
	}
}
