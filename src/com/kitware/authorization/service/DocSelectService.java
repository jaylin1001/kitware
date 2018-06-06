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
	public List<DocVO> selectGJWait(String emp_num) throws Exception{
		return dao.selectGJWait(emp_num);
	}
	public List<DocVO> findIng(String emp_num) throws Exception {
		return dao.selectIng(emp_num);
	}

	public List<DocVO> findOk(String emp_num) throws Exception {
		System.out.println(dao.selectOk(emp_num));
		return dao.selectOk(emp_num);
	}
	
	public List<DocVO> selectCancle(String emp_num) throws Exception{
		return dao.selectCancle(emp_num);
	}
	public List<DocVO> selectZero(String emp_num) throws Exception{
		return dao.selectZero(emp_num);
	}
	
	public List<DocVO> selectExpected(String conf_num) throws Exception{
		return dao.selectExpected(conf_num);
		
	}
	public List<DocVO> selectmyAll(String emp_num) throws Exception{
		return dao.selectmyAll(emp_num);
	}
	public List<DocVO> selectOK(String emp_num) throws Exception{
		return dao.selectOk(emp_num);
	}
	
	public List<DocVO> selectGJOk(String conf_num, String state) throws Exception{
		return dao.selectGJOk(conf_num, state);
	}
	public List<DocVO> selectGJOkAll(String conf_num) throws Exception{
		return dao.selectGJOkAll(conf_num);
	}

	public List<DocVO> selectDeptlistAll(String dept_num) throws Exception{
		return dao.selectDeptlistAll(dept_num);
	}
	public List<DocVO> selectDeptlist(String dept_num, String state) throws Exception{
		return dao.selectDeptlist(dept_num, state);
	}
	public DocVO selectAll(String doc_num) throws Exception{
		System.out.println(dao.selectAll(doc_num));
		return dao.selectAll(doc_num);
		
	}
	public DocVO selectAllRefer(String doc_num) throws Exception{
		System.out.println(dao.selectAll(doc_num));
		return dao.selectAllRefer(doc_num);
		
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
