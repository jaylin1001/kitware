package com.kitware.authorization.dao;

import java.sql.Connection;
import java.util.List;

import com.kitware.authorization.vo.DocDetailVO;
import com.kitware.authorization.vo.DocGiganVO;
import com.kitware.authorization.vo.DocVO;
import com.kitware.member.vo.DeptInfo;

public interface DocDAO {
	public int selectCount() throws Exception;//totalcnt값 가져옴
	public DocVO selectAll(String doc_num) throws Exception; //Document 내용 뿌려줌
	public List<DocDetailVO> selectConf(String doc_num) throws Exception; //Document에 대한 conf_num(결재자) 가져오기
	public List<DocVO> selectGJWait(String emp_num) throws Exception; //내가 당장 결재 해야하는  문서 리스트
	public List<DocVO> selectIng(String emp_num) throws Exception; //진행문서 select (내가 올린거)
	public List<DocVO> selectmyAll(String emp_num) throws Exception; //내가 올린 기안 다 보기(page 매개변수 추가)
	public List<DocVO> selectOk(String emp_num) throws Exception; //내가 올린 기안 완료   
	public List<DocVO> selectCancle(String emp_num) throws Exception;//내가 올린 기안 취소
	public List<DocVO> selectExpected(String conf_num) throws Exception; //언젠가 결재예정 select 
	public List<DocVO> selectGJOk(String conf_num) throws Exception; //내가 결재완료한거(결재완료 사이드메뉴 해당)
	public List<DocVO> selectGJOk1(String conf_num) throws Exception; //내가 결재완료한거(결재완료 사이드메뉴 해당)
	public List<DocVO> selectGJOk2(String conf_num) throws Exception; //내가 결재완료한거(결재완료 사이드메뉴 해당)
	public List<DocVO> selectGJOk3(String conf_num) throws Exception; //내가 결재완료한거(결재완료 사이드메뉴 해당)
	public List<DocVO> selectDeptlist(String dept_num, String state) throws Exception;//부서문서함 리스트
	public void updateCJ(DocVO docvo, String doc_num) throws Exception;//출장에 대한 글 수정 document
	public void updateCJ1(DocGiganVO docvo, String doc_num) throws Exception;//출장에 대한 글 수정 doc_gigan
	public void deleteDoc(String doc_num) throws Exception;//문서 삭제
	public String getEmpNum(String g1, String g1_grade) throws Exception;
	public int insertDocGigan(DocGiganVO doc_gigan, Connection con) throws Exception ;
	public int insertGianDetail(DocDetailVO docdetail, Connection con, int sunbeon) throws Exception;
	public int insertGian(DocVO giandoc, Connection con) throws Exception;
	public void insertGianfull(DocVO giandoc) throws Exception;
	public List<DeptInfo> getDeptList() throws Exception;
	public String getMaxDocNum() throws Exception;
	public void updateConf(String doc_num, String conf_num, String acs_yn) throws Exception; //결재버튼시 발생
	public void updateState(String doc_num, String state) throws Exception; //문서 상태 2(완료)변경
	//다 구현한거 아니고 추가해야됩니다
	public List<DocVO> selectGJOk3(String conf_num, int page) throws Exception;
	public List<DocVO> selectGJOk2(String conf_num, int page) throws Exception;
	public List<DocVO> selectGJOk1(String conf_num, int page) throws Exception;
}