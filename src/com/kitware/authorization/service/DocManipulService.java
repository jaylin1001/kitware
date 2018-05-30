package com.kitware.authorization.service;

import com.kitware.authorization.dao.DocDAO;
import com.kitware.authorization.dao.DocDAOOracle;
import com.kitware.authorization.vo.DocGiganVO;
import com.kitware.authorization.vo.DocVO;

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
	
	public DocManipulService() {

	}

	public static DocManipulService getInstance() {
		if (service == null) {
			service = new DocManipulService();
		}
		return service;
	}
}
