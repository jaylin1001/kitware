package com.kitware.authorization.service;

import java.util.List;

import com.kitware.authorization.dao.DocDAO;
import com.kitware.authorization.dao.DocDAOOracle;
import com.kitware.authorization.vo.DocDetailVO;
import com.kitware.authorization.vo.DocGiganVO;
import com.kitware.authorization.vo.DocVO;
import com.kitware.member.vo.DeptInfo;

public class DocManipulService {
	static private DocManipulService service;
	private DocDAO dao = new DocDAOOracle();
	
	public void updateCJ(DocVO docvo, String doc_num) throws Exception{
		 dao.updateCJ(docvo, doc_num);
		}
	
	public void updateCJ1(DocGiganVO docggvo, String doc_num) throws Exception{
		dao.updateCJ1(docggvo, doc_num);
	}
	public void deleteDoc(String doc_num) throws Exception{
		dao.deleteDoc(doc_num);
	}

	public List<DeptInfo> getDeptList() throws Exception {
		return dao.getDeptList();
	}
	
	public String getDocNum() throws Exception {
		return dao.getMaxDocNum();
	}

	public String getEmpNum(String g1, String g1_grade) throws Exception {
		return dao.getEmpNum(g1, g1_grade);
	}

	public void insertgian(DocVO giandoc) throws Exception {
		dao.insertGianfull(giandoc);
	}
	public void updateConf(String doc_num, String conf_num, String acs_yn) throws Exception{
		dao.updateConf(doc_num, conf_num, acs_yn);
	}
	public void updateState(String doc_num, String state) throws Exception{
		dao.updateState(doc_num, state);
	}
	
	public DocManipulService() {

	}

	public static DocManipulService getInstance() {
		if (service == null) {
			service = new DocManipulService();
		}
		return service;
	}
}
