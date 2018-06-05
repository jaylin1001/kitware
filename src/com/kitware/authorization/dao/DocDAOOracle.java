package com.kitware.authorization.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kitware.authorization.vo.DocDetailVO;
import com.kitware.authorization.vo.DocGiganVO;
import com.kitware.authorization.vo.DocKindVO;
import com.kitware.authorization.vo.DocVO;
import com.kitware.member.vo.DeptInfo;
import com.kitware.member.vo.GradeInfo;
import com.kitware.member.vo.Members;

public class DocDAOOracle implements DocDAO {

	public int selectCount() throws Exception {
		// totalcnt 가져오기
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectCountSQL = "SELECT COUNT(*) doc_num FROM document";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectCountSQL);
			rs = pstmt.executeQuery();
			rs.next();
			int totalCount = rs.getInt("doc_num");
			return totalCount;
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public DocVO selectAll(String doc_num) throws Exception {
		// 문서 상세보기
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectAllSQL = "select d.doc_num,d.doc_state, d.emp_num, dk.doc_name,d.start_date,d.rcv_dept,"
				+ " d.refer,d.doc_title, dg.start_date, dg.end_date, d.doc_content, m.name, gi.position_name, di.dept_name"
				+ " from document d, doc_kind dk, doc_gigan dg, members m, grade_info gi, dept_info di"
				+ " where dk.doc_kind = d.doc_kind" + " and m.emp_num = d.emp_num"
				+ " and m.position_num = gi.position_num" + " and m.dept_num = di.dept_num" + " and d.doc_num = ?";

		DocVO docvo = null; // doc 데이터 담음
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectAllSQL);
			pstmt.setString(1, doc_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				docvo = new DocVO();
				docvo.setDoc_num(rs.getString("doc_num"));
				docvo.setDoc_state(rs.getString("doc_state"));
				DocKindVO dock = new DocKindVO(docvo.getDoc_kind(), rs.getString("doc_name"));
				docvo.setStart_date(rs.getString("start_date"));
				docvo.setRcv_dept(rs.getString("rcv_dept"));
				docvo.setRefer(rs.getString("refer"));
				docvo.setDoc_title(rs.getString("doc_title"));
				DocGiganVO giganvo = new DocGiganVO(rs.getString("start_date"), rs.getString("end_date"));
				Members mem = new Members(rs.getString("name"));
				GradeInfo gradeinfo = new GradeInfo(rs.getString("position_name"));
				DeptInfo deptinfo = new DeptInfo(rs.getString("dept_name"));
				docvo.setDoc_content(rs.getString("doc_content"));
				docvo.setDoc_kindvo(dock);
				docvo.setDoc_gigan(giganvo);
				docvo.setMembers(mem);
				docvo.setGradeinfo(gradeinfo);
				docvo.setDeptinfo(deptinfo);
			}

		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return docvo;
	}
	@Override
	public DocVO selectAllRefer(String doc_num) throws Exception {
		// 문서 상세보기
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectAllSQL = "select d.doc_num,d.doc_state, d.emp_num, dk.doc_name,d.start_date,d.rcv_dept,"
				+ " m2.name refer,d.doc_title, dg.start_date, dg.end_date, d.doc_content, m.name, gi.position_name, di.dept_name"
				+ " from document d, doc_kind dk, doc_gigan dg, members m, members m2, grade_info gi, dept_info di"
				+ " where dk.doc_kind = d.doc_kind" + " and m.emp_num = d.emp_num and d.refer=m2.emp_num"
				+ " and m.position_num = gi.position_num" + " and m.dept_num = di.dept_num" 
				+ " and dg.doc_num = d.doc_num"
				+ " and d.doc_num = ?";
		
		DocVO docvo = null; // doc 데이터 담음
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectAllSQL);
			pstmt.setString(1, doc_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				docvo = new DocVO();
				docvo.setDoc_num(rs.getString("doc_num"));
				docvo.setDoc_state(rs.getString("doc_state"));
				DocKindVO dock = new DocKindVO(docvo.getDoc_kind(), rs.getString("doc_name"));
				docvo.setStart_date(rs.getString("start_date"));
				docvo.setRcv_dept(rs.getString("rcv_dept"));
				docvo.setRefer(rs.getString("refer"));
				docvo.setDoc_title(rs.getString("doc_title"));
				DocGiganVO giganvo = new DocGiganVO(rs.getString("start_date"), rs.getString("end_date"));
				Members mem = new Members(rs.getString("name"));
				GradeInfo gradeinfo = new GradeInfo(rs.getString("position_name"));
				DeptInfo deptinfo = new DeptInfo(rs.getString("dept_name"));
				docvo.setDoc_content(rs.getString("doc_content"));
				docvo.setDoc_kindvo(dock);
				docvo.setDoc_gigan(giganvo);
				docvo.setMembers(mem);
				docvo.setGradeinfo(gradeinfo);
				docvo.setDeptinfo(deptinfo);
			}
			
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return docvo;
	}

	@Override
	public List<DocDetailVO> selectConf(String doc_num) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectConfSQL = "select dd.conf_num ,dd.acs_yn, m.name" + " from doc_detail dd, members m"
				+ " where m.emp_num =dd.conf_num" + " and dd.doc_num =? order by dd.sunbeon";
		List<DocDetailVO> doc_detaillist = new ArrayList<>();
		DocDetailVO doc_detailvo = null; // doc_detail 데이터 담음

		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectConfSQL);
			pstmt.setString(1, doc_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				doc_detailvo = new DocDetailVO();
				doc_detailvo.setConf_num(rs.getString("conf_num"));
				doc_detailvo.setAcs_yn(rs.getString("acs_yn"));
				Members mems = new Members(rs.getString("name"));
				doc_detailvo.setMembers(mems);
				doc_detaillist.add(doc_detailvo);
			}

		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return doc_detaillist;
	}

	@Override
	public List<DocVO> selectGJWait(String emp_num) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectGJWaitSQL = " select rownum r, mydocnum.doc_num, mydocnum.doc_kind, mydocnum.doc_title,"
								+" mydocnum.start_date, mydocnum.doc_state, dk.doc_name"
								+" from(select doc.* from"
								+" (select d.*, dd.conf_num from document d, doc_detail dd"
								+" where d.doc_num = dd.doc_num) doc,"
								+" (select doc_num from doc_detail"
								+" where conf_num = ?) myconf" 
								+" where doc.doc_num = myconf.doc_num) mydocnum, doc_kind dk,"
								+" (select distinct a.doc_num from (select doc_num, sunbeon, acs_yn from doc_detail) a,"
								+" (select doc_num, sunbeon, conf_num, acs_yn from doc_detail where conf_num = ?) b"
								+" where a.doc_num = b.doc_num and b.conf_num = ? and"
								+" ((a.sunbeon = b.sunbeon-1 and a.acs_yn = 1 and b.acs_yn = 0) or (b.sunbeon=1 and b.acs_yn=0)))"
								+" preconf where mydocnum.doc_num = preconf.doc_num"
								+" and mydocnum.conf_num = ?"
								+" and mydocnum.doc_kind = dk.doc_kind and mydocnum.doc_state !='3' order by mydocnum.doc_num desc";
		List<DocVO> doclist = new ArrayList<>(); // 사이즈 변경 가능하며 null허용하는 arraylist
		DocVO docvo = null; // doc 데이터 담음
		DocKindVO dock = new DocKindVO();// dockind 데이터 담음
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectGJWaitSQL);
			pstmt.setString(1, emp_num);
			pstmt.setString(2, emp_num);
			pstmt.setString(3, emp_num);
			pstmt.setString(4, emp_num);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				docvo = new DocVO();
				docvo.setDoc_num(rs.getString("doc_num"));
				docvo.setDoc_kind(rs.getInt("doc_kind"));
				docvo.setDoc_title(rs.getString("doc_title"));
				docvo.setStart_date(rs.getString("start_date"));
				docvo.setDoc_state(rs.getString("doc_state"));
				dock = new DocKindVO(docvo.getDoc_kind(), rs.getString("doc_name"));
				docvo.setDoc_kindvo(dock);
				doclist.add(docvo);
			}
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return doclist;
	}

	@Override
	public List<DocVO> selectIng(String emp_num) throws Exception {
		// Main의 진행문서(내가올린거) table list 뿌려주는 select
		// 매개변수 id 보여주는 갯수 제한 필요

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectOkSQL = "select d.start_date, d.doc_kind, d.doc_title, d.doc_num, d.doc_state, dk.doc_name"
				+ " from document d, doc_kind dk" + " where d.doc_kind = dk.doc_kind" + " and d.doc_state in('0','1')"
				+ " and d.emp_num = ? order by d.doc_num desc";
		List<DocVO> doclist2 = new ArrayList<>(); // 사이즈 변경 가능하며 null허용하는 arraylist
		DocVO docvo2 = null; // doc 데이터 담음
		DocKindVO dock2 = new DocKindVO();// dockind 데이터 담음
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectOkSQL);
			pstmt.setString(1, emp_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				docvo2 = new DocVO();
				docvo2.setStart_date(rs.getString("start_date"));
				docvo2.setDoc_title(rs.getString("doc_title"));
				docvo2.setDoc_num(rs.getString("doc_num"));
				docvo2.setDoc_state(rs.getString("doc_state"));
				docvo2.setDoc_kind(rs.getInt("doc_kind"));
				dock2 = new DocKindVO(docvo2.getDoc_kind(), rs.getString("doc_name"));
				docvo2.setDoc_kindvo(dock2);
				doclist2.add(docvo2);
			}

		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return doclist2;
	}


	@Override
	public List<DocVO> selectmyAll(String emp_num) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectIngSQL = "select rownum r, d.start_date, d.doc_kind,d.doc_title, d.doc_num, d.doc_state, dk.doc_name"
				+ " from document d, doc_kind dk" + " where d.doc_kind = dk.doc_kind" + " and d.emp_num = ?"
				+ " order by doc_num desc";

		List<DocVO> doclist = new ArrayList<>(); // 사이즈 변경 가능하며 null허용하는 arraylist
		DocVO docvo = null; // doc 데이터 담음

		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectIngSQL);
			pstmt.setString(1, emp_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				docvo = new DocVO();
				docvo.setStart_date(rs.getString("start_date"));
				docvo.setDoc_kind(rs.getInt("doc_kind"));
				docvo.setDoc_title(rs.getString("doc_title"));
				docvo.setDoc_num(rs.getString("doc_num"));
				docvo.setDoc_state(rs.getString("doc_state"));
				DocKindVO dock = new DocKindVO(docvo.getDoc_kind(), rs.getString("doc_name"));
				docvo.setDoc_kindvo(dock);
				doclist.add(docvo);
			}

		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return doclist;
	}
	
	
	
	@Override
	public List<DocVO> selectOk(String emp_num) throws Exception { // 내가올린거 상태 완료인 문서 list로 뿌려주는 select

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectOkSQL = "select d.doc_num, d.doc_title, d.doc_state, d.start_date, dk.doc_name, d.doc_kind"
				+" from document d, doc_detail dd, doc_kind dk where d.doc_num = dd.doc_num"
				+" and d.doc_kind = dk.doc_kind and d.emp_num =?"
				+" and d.doc_state = 2 order by d.doc_num desc";
		List<DocVO> doclist2 = new ArrayList<>(); // 사이즈 변경 가능하며 null허용하는 arraylist
		DocVO docvo2 = null; // doc 데이터 담음
		DocKindVO dock2 = new DocKindVO();// dockind 데이터 담음
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectOkSQL);
			pstmt.setString(1, emp_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				docvo2 = new DocVO();
				docvo2.setStart_date(rs.getString("start_date"));
				docvo2.setDoc_title(rs.getString("doc_title"));
				docvo2.setDoc_num(rs.getString("doc_num"));
				docvo2.setDoc_state(rs.getString("doc_state"));
				dock2 = new DocKindVO(docvo2.getDoc_kind(), rs.getString("doc_name"));
				docvo2.setDoc_kindvo(dock2);
				doclist2.add(docvo2);
			}

		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return doclist2;
	}

	@Override
	public List<DocVO> selectCancle(String emp_num) throws Exception {
		// 내가 올린 기안 취소
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectSQL = "select rownum r, d.start_date, dk.doc_name, d.doc_title, d.doc_num, d.doc_state, d.doc_kind"
				+" from document d, doc_kind dk" + " where d.doc_kind = dk.doc_kind" + " and d.doc_state = 3"
				+" and d.emp_num = ? order by d.doc_num desc";
		List<DocVO> doclist2 = new ArrayList<>(); // 사이즈 변경 가능하며 null허용하는 arraylist
		DocVO docvo2 = null; // doc 데이터 담음
		DocKindVO dock2 = new DocKindVO();// dockind 데이터 담음
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, emp_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				docvo2 = new DocVO();
				docvo2.setStart_date(rs.getString("start_date"));
				docvo2.setDoc_title(rs.getString("doc_title"));
				docvo2.setDoc_num(rs.getString("doc_num"));
				docvo2.setDoc_state(rs.getString("doc_state"));
				docvo2.setDoc_kind(rs.getInt("doc_kind"));
				dock2 = new DocKindVO(docvo2.getDoc_kind(), rs.getString("doc_name"));
				docvo2.setDoc_kindvo(dock2);
				doclist2.add(docvo2);
			}

		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return doclist2;

	}

	@Override
	public List<DocVO> selectExpected(String conf_num) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectExpectedSQL = "select  rownum r, d.doc_num, d.doc_title, d.doc_state, d.start_date, dk.doc_name, d.doc_kind"
				+ " from document d, doc_detail dd, doc_kind dk" + " where d.doc_num = dd.doc_num"
				+ " and d.doc_kind = dk.doc_kind" + " and dd.conf_num = ?" + " and dd.acs_yn = 0 and d.doc_state !=3 order by d.doc_num desc";
		List<DocVO> doclist2 = new ArrayList<>(); // 이부분부터 수정들어가야함 0525 오후 4:43
		DocVO docvo2 = null; // doc 데이터 담음
		DocKindVO dock2 = new DocKindVO();// dockind 데이터 담음
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectExpectedSQL);
			pstmt.setString(1, conf_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				docvo2 = new DocVO();
				docvo2.setDoc_num(rs.getString("doc_num"));
				docvo2.setDoc_title(rs.getString("doc_title"));
				docvo2.setDoc_state(rs.getString("doc_state"));
				docvo2.setStart_date(rs.getString("start_date"));
				docvo2.setDoc_kind(rs.getInt("doc_kind"));
				dock2 = new DocKindVO(docvo2.getDoc_kind(), rs.getString("doc_name"));
				docvo2.setDoc_kindvo(dock2);
				doclist2.add(docvo2);
			}

		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return doclist2;

	}

	@Override
	public List<DocVO> selectGJOk(String conf_num, String state) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectGJOkSQL = "select  rownum r, d.doc_num, d.doc_title, d.doc_state, d.start_date, dk.doc_name, d.doc_kind"
				+ " from document d, doc_detail dd, doc_kind dk" + " where d.doc_num = dd.doc_num"
				+ " and d.doc_kind = dk.doc_kind" + " and conf_num = ?" + " and d.doc_state in(?) order by d.doc_num desc";
		List<DocVO> doclist2 = new ArrayList<>(); // 이부분부터 수정들어가야함 0525 오후 4:43
		DocVO docvo2 = null; // doc 데이터 담음
		DocKindVO dock2 = new DocKindVO();// dockind 데이터 담음
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectGJOkSQL);
			pstmt.setString(1, conf_num);
			pstmt.setString(2, state);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				docvo2 = new DocVO();
				docvo2.setDoc_num(rs.getString("doc_num"));
				docvo2.setDoc_title(rs.getString("doc_title"));
				docvo2.setDoc_state(rs.getString("doc_state"));
				docvo2.setStart_date(rs.getString("start_date"));
				dock2 = new DocKindVO(docvo2.getDoc_kind(), rs.getString("doc_name"));
				docvo2.setDoc_kind(rs.getInt("doc_kind"));
				docvo2.setDoc_kindvo(dock2);
				doclist2.add(docvo2);
			}

		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return doclist2;
	}
	@Override
	public List<DocVO> selectGJOkAll(String conf_num) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectGJOkSQL = "select  rownum r, d.doc_num, d.doc_title, d.doc_state, d.start_date, dk.doc_name, d.doc_kind"
				+ " from document d, doc_detail dd, doc_kind dk" + " where d.doc_num = dd.doc_num"
				+ " and d.doc_kind = dk.doc_kind" + " and conf_num = ?" + " and acs_yn in(0,1,2,3) order by d.doc_num desc";
		List<DocVO> doclist2 = new ArrayList<>(); // 이부분부터 수정들어가야함 0525 오후 4:43
		DocVO docvo2 = null; // doc 데이터 담음
		DocKindVO dock2 = new DocKindVO();// dockind 데이터 담음
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectGJOkSQL);
			pstmt.setString(1, conf_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				docvo2 = new DocVO();
				docvo2.setDoc_num(rs.getString("doc_num"));
				docvo2.setDoc_title(rs.getString("doc_title"));
				docvo2.setDoc_state(rs.getString("doc_state"));
				docvo2.setStart_date(rs.getString("start_date"));
				dock2 = new DocKindVO(docvo2.getDoc_kind(), rs.getString("doc_name"));
				docvo2.setDoc_kind(rs.getInt("doc_kind"));
				docvo2.setDoc_kindvo(dock2);
				doclist2.add(docvo2);
			}
			
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return doclist2;
	}
	
	@Override
	public List<DocVO> selectDeptlist(String dept_num ,String state) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectDeptlistSQL = "select d.doc_num, d.doc_kind, d.doc_title, d.doc_state, d.start_date, dk.doc_name"
								+" from document d, members m, doc_kind dk"
								+" where d.emp_num = m.emp_num"
								+" and d.doc_kind = dk.doc_kind"
								+" and m.dept_num = ?"
								+" and d.doc_state in(?) order by d.doc_num desc";
		List<DocVO> doclist2 = new ArrayList<>();
		DocVO docvo2 = null; // doc 데이터 담음
		DocKindVO dock2 = new DocKindVO();// dockind 데이터 담음
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectDeptlistSQL);
			pstmt.setString(1, dept_num);
			pstmt.setString(2, state);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				docvo2 = new DocVO();
				docvo2.setDoc_num(rs.getString("doc_num"));
				docvo2.setDoc_kind(rs.getInt("doc_kind"));
				docvo2.setDoc_title(rs.getString("doc_title"));
				docvo2.setDoc_state(rs.getString("doc_state"));
				docvo2.setStart_date(rs.getString("start_date"));
				dock2 = new DocKindVO(docvo2.getDoc_kind(), rs.getString("doc_name"));
				docvo2.setDoc_kindvo(dock2);
				doclist2.add(docvo2);
			}

		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return doclist2;

	}

	@Override
	public List<DocVO> selectDeptlistAll(String dept_num) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectDeptlistSQL = "select d.doc_num, d.doc_kind, d.doc_title, d.doc_state, d.start_date, dk.doc_name"
								+" from document d, members m, doc_kind dk"
								+" where d.emp_num = m.emp_num"
								+" and d.doc_kind = dk.doc_kind"
								+" and m.dept_num = ?"
								+" and d.doc_state in(0,1,2,3) order by d.doc_num desc";
		List<DocVO> doclist2 = new ArrayList<>();
		DocVO docvo2 = null; // doc 데이터 담음
		DocKindVO dock2 = new DocKindVO();// dockind 데이터 담음
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectDeptlistSQL);
			pstmt.setString(1, dept_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				docvo2 = new DocVO();
				docvo2.setDoc_num(rs.getString("doc_num"));
				docvo2.setDoc_kind(rs.getInt("doc_kind"));
				docvo2.setDoc_title(rs.getString("doc_title"));
				docvo2.setDoc_state(rs.getString("doc_state"));
				docvo2.setStart_date(rs.getString("start_date"));
				dock2 = new DocKindVO(docvo2.getDoc_kind(), rs.getString("doc_name"));
				docvo2.setDoc_kindvo(dock2);
				doclist2.add(docvo2);
			}

		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return doclist2;

	}
	

	@Override
	public void updateCJ1(DocGiganVO docvo, String doc_num) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String updateCJSQL = "update doc_gigan" + " set start_date = ?," + " end_date = ?" + " where doc_num = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(updateCJSQL);
			pstmt.setString(1, docvo.getStart_date());
			pstmt.setString(2, docvo.getEnd_date());
			pstmt.setString(3, doc_num);
			int n = pstmt.executeUpdate();
			System.out.println("123" + n);

		} finally {
			MyConnection.close(rs, pstmt, con);
		}

	}

	@Override
	public void updateCJ(DocVO docvo, String doc_num) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String updateCJSQL = "update document" + " set doc_title = ?," + " doc_content = ?" + " where doc_num = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(updateCJSQL);
			pstmt.setString(1, docvo.getDoc_title());
			pstmt.setString(2, docvo.getDoc_content());
			pstmt.setString(3, doc_num);
			int n = pstmt.executeUpdate();
			System.out.println("234" + n);

		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public void deleteDoc(String doc_num) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String deleteSQL = "delete from document where doc_num = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(deleteSQL);
			pstmt.setString(1, doc_num);
			pstmt.executeUpdate();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}

	/*
	 * public static void main(String[] args) { DocDAOOracle test = new
	 * DocDAOOracle(); try { DocVO list = test.selectAll("1805-0001");
	 * System.out.println(list);
	 * 
	 * 
	 * }catch(Exception e) { e.printStackTrace(); } }
	 */

	@Override
	public String getMaxDocNum() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String num = null;
		String getnumSql = "select max(doc_num) from document";

		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(getnumSql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return num;
	}

	@Override
	public List<DeptInfo> getDeptList() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectDeptInfoSQL = "SELECT *\r\n" + "FROM dept_info\r\n" + "ORDER BY dept_num";
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectDeptInfoSQL);
			rs = pstmt.executeQuery();
			List<DeptInfo> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new DeptInfo(rs.getString("dept_num"), rs.getString("dept_name")));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			com.kitware.sql.MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public void insertGianfull(DocVO giandoc) throws Exception {
		Connection con = null;
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			con.setAutoCommit(false);
			int insertgian = insertGian(giandoc, con);
			System.out.println("insertgian ++"+insertgian);
			int insertgigan = insertDocGigan(giandoc.getDoc_gigan(), con);
			System.out.println("insertgigan"+insertgigan);
			List<DocDetailVO> list = giandoc.getDoc_detail();
			int sunbeon = 0;
			int cnt = 0;
			if (list.size() > 0) {
				for (DocDetailVO detail : list) {
					int insertdetail = insertGianDetail(detail, con, ++sunbeon);
					System.out.println(sunbeon);
					if (insertdetail > 0)
						cnt++;
				}
				System.out.println("list++"+list.size());
				System.out.println("cnt++"+cnt);
				if (insertgian > 0 && insertgigan > 0 && cnt == list.size()) {
					System.out.println("commit!");
					System.out.println(list.size() + "::" + cnt);
					con.commit();
				}
			}
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace(); // 톰캣콘솔
			throw e;
		} finally {
			com.kitware.sql.MyConnection.close(null, con);
		}
	}

	@Override
	public int insertGian(DocVO giandoc, Connection con) throws Exception {
		System.out.println("document");
		Connection conn = con;
		PreparedStatement pstmt = null;
		String insertgianSQL = "insert into document (doc_num, doc_kind, emp_num, doc_state, doc_title, doc_content, start_date, rcv_dept, refer)\r\n"
				+ "values(?,?,?,0,?,to_nclob(?),?,?,?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(insertgianSQL);
			int index = 0;
			pstmt.setString(++index, giandoc.getDoc_num());
			pstmt.setInt(++index, giandoc.getDoc_kind());
			pstmt.setString(++index, giandoc.getEmp_num());
			pstmt.setString(++index, giandoc.getDoc_title());
			pstmt.setString(++index, giandoc.getDoc_content());
			pstmt.setString(++index, giandoc.getStart_date());
			pstmt.setString(++index, giandoc.getRcv_dept());
			pstmt.setString(++index, giandoc.getRefer());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(); // 톰캣콘솔
			throw e;
		} finally {
			com.kitware.sql.MyConnection.close(pstmt);
		}
		System.out.println(result);
		return result;
	}

	@Override
	public int insertGianDetail(DocDetailVO docdetail, Connection con, int sunbeon) throws Exception {
		System.out.println("deatail");
		System.out.println(docdetail.getConf_num());
		Connection conn = con;
		PreparedStatement pstmt = null;
		String insertgianSQL = "insert into doc_detail (doc_num, conf_num, sunbeon, acs_yn, rcv_date)\r\n"
				+ "values(?,?,?,0,sysdate)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(insertgianSQL);
			int index = 0;
			pstmt.setString(++index, docdetail.getDoc_num());
			pstmt.setString(++index, docdetail.getConf_num());
			pstmt.setInt(++index, sunbeon);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(); // 톰캣콘솔
			throw e;
		} finally {
			com.kitware.sql.MyConnection.close(pstmt);
		}
		return result;
	}

	@Override
	public int insertDocGigan(DocGiganVO doc_gigan, Connection con) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("gigan");
		Connection conn = con;
		PreparedStatement pstmt = null;
		String insertgianSQL = "insert into doc_gigan (doc_num, start_date, end_date)\r\n" + "values(?,?,?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(insertgianSQL);
			int index = 0;
			pstmt.setString(++index, doc_gigan.getDoc_num());
			pstmt.setString(++index, doc_gigan.getStart_date());
			pstmt.setString(++index, doc_gigan.getEnd_date());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(); // 톰캣콘솔
			throw e;
		} finally {
			com.kitware.sql.MyConnection.close(pstmt);
		}
		return result;
	}

	@Override
	public String getEmpNum(String g1, String g1_grade) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String emp_num = null;
		String selectMemberByName = "SELECT m.emp_num\r\n" + "FROM members m, grade_info g\r\n"
				+ "where g.position_num = m.position_num and g.position_name=? and m.name=?";
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectMemberByName);
			pstmt.setString(1, g1_grade);
			pstmt.setString(2, g1);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				emp_num = rs.getString("emp_num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			com.kitware.sql.MyConnection.close(rs, pstmt, con);
		}
		return emp_num;
	}

	@Override
	public void updateConf(String doc_num, String conf_num, String acs_yn) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String updateConfSQL = "update doc_detail" + " set acs_yn = ?" + " where doc_num = ?" + " and conf_num = ?";

		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(updateConfSQL);
			pstmt.setString(1, acs_yn);
			pstmt.setString(2, doc_num);
			pstmt.setString(3, conf_num);
			pstmt.executeUpdate();

		} finally {
			MyConnection.close(rs, pstmt, con);
		}

	}

	@Override
	public void updateState(String doc_num, String state) throws Exception {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String updateConfSQL = "update document" + " set doc_state = ?" + " where doc_num = ?";

		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(updateConfSQL);
			pstmt.setString(1, state);
			pstmt.setString(2, doc_num);
			pstmt.executeUpdate();

		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}
	public static void main(String[] args) {
		DocDAOOracle test = new DocDAOOracle();
		try {
			
			DocVO list = test.selectAll("1806-0005");
			System.out.println(list);	
			DocVO list2 = test.selectAllRefer("1806-0002");
			System.out.println(list2);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	

	
}
