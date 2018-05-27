package com.kitware.authorization.service;

import java.util.List;

import com.kitware.authorization.dao.DocDAO;
import com.kitware.authorization.dao.DocDAOOracle;
import com.kitware.authorization.vo.DocDetailVO;
import com.kitware.authorization.vo.DocVO;

public class DocSelectService {

	static private DocSelectService service;
	private DocDAO dao = new DocDAOOracle();
	
	public int findCount() throws Exception{
		return dao.selectCount();
	}

	public List<DocVO> findIng(String emp_num) throws Exception {
		System.out.println(dao.selectIng(emp_num));
		return dao.selectIng(emp_num, 1);
	}
	public List<DocVO> findIng(String emp_num, int page) throws Exception{
		System.out.println("service"+dao.selectIng(emp_num, page));
		return dao.selectIng(emp_num, page);

	}

	public List<DocVO> findOk(String emp_num) throws Exception {
		System.out.println(dao.selectOk(emp_num));
		return dao.selectOk(emp_num, 1);
	}
	
	public List<DocVO> findOk(String emp_num, int page) throws Exception {
		return dao.selectOk(emp_num, page);
	}
	
	public List<DocVO> selectExpected(String conf_num, int page) throws Exception{
		return dao.selectExpected(conf_num, page);
		
	}
	public List<DocVO> selectGJOk(String conf_num, int page) throws Exception{
		return dao.selectGJOk(conf_num, page);
		
	}
	public DocVO selectAll(String doc_num) throws Exception{
		System.out.println(dao.selectAll(doc_num));
		return dao.selectAll(doc_num);
		
	}
	public List<DocDetailVO> selectConf(String doc_num) throws Exception{
		System.out.println(dao.selectConf(doc_num));
		return dao.selectConf(doc_num);
	}

	public DocSelectService() {

	}

	public static DocSelectService getInstance() {
		if (service == null) {
			service = new DocSelectService();
		}
		return service;
	}
}
