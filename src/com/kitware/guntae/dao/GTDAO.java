package com.kitware.guntae.dao;

import java.util.List;

import com.kitware.authorization.vo.DocGiganVO;
import com.kitware.guntae.vo.Gunte;
import com.kitware.guntae.vo.Yeoncha;

public interface GTDAO {
	//근태 모든것 셀렉트
	List<Gunte> gselectAll(String emp_num) throws Exception;
	//근태 출석 인설트
	void ininsert(String emp_num) throws Exception; 
	//근태 퇴근 업데이트
	void outupdate(String emp_num) throws Exception;
	//근태 상태 셀렉트
	List<DocGiganVO> dockindselectAll(String emp_num, String doc_kind) throws Exception;
	
	//근태 출근시간 조정 Gunte_standard
	
	//연차 모든것 셀렉트
	Yeoncha selectAll(String emp_num, String years) throws Exception;
	//연차 총 사용기간 업데이트
	void useupdate(String emp_num, String years) throws Exception;
	//연차기간 모든것 셀렉트
	public List<List<String>> giganselectAll(String emp_num, String years) throws Exception;
	//연차기간 doc_gigan에서 인설트 //삭제예정 -> 결제로
	public void giganinsert(String emp_num) throws Exception;
}
