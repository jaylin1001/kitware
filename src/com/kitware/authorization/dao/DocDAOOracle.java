package com.kitware.authorization.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kitware.authorization.vo.DocDetailVO;
import com.kitware.authorization.vo.DocGiganVO;
import com.kitware.authorization.vo.DocKindVO;
import com.kitware.authorization.vo.DocVO;
import com.kitware.member.vo.Members;


public class DocDAOOracle implements DocDAO {
	
	public int selectCount() throws Exception{
		//totalcnt 가져오기
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectCountSQL = 
				"SELECT COUNT(*) doc_num FROM document";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectCountSQL);
			rs = pstmt.executeQuery();
			rs.next();
			int totalCount = rs.getInt("doc_num");
			return totalCount;
		}finally {
			MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public DocVO selectAll(String doc_num) throws Exception {
		//문서 상세보기
		//매개변수 id 보여주는 갯수 제한 필요
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectAllSQL = "select d.doc_num, dk.doc_name,d.start_date,d.rcv_dept,"
							+" d.refer,d.doc_title, dg.start_date, dg.end_date, d.doc_content"
							+" from document d, doc_kind dk, doc_gigan dg"
							+" where dk.doc_kind = d.doc_kind"
							+" and d.doc_num = ?";
		
		//List<DocVO> doclist = new ArrayList<>(); 
		DocVO docvo = null;	//doc 데이터 담음
		
	try {
		con= MyConnection.getConnection();
		pstmt = con.prepareStatement(selectAllSQL);
		pstmt.setString(1, doc_num);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			docvo = new DocVO();
			docvo.setDoc_num(rs.getString("doc_num"));
			DocKindVO dock = new DocKindVO(docvo.getDoc_kind(),rs.getString("doc_name"));
			docvo.setStart_date(rs.getString("start_date"));
			docvo.setRcv_dept(rs.getString("rcv_dept"));
			docvo.setRefer(rs.getString("refer"));
			docvo.setDoc_title(rs.getString("doc_title"));
			DocGiganVO giganvo = new DocGiganVO(rs.getString("start_date"),
						   rs.getString("end_date"));
			docvo.setDoc_content(rs.getString("doc_content"));
			docvo.setDoc_kindvo(dock);
			docvo.setDoc_gigan(giganvo);
			/*doclist.add(docvo);*/
		}
		
	}finally{
		MyConnection.close(rs,pstmt,con);
	}
		return docvo;
	}
	
	@Override
	public List<DocDetailVO> selectConf(String doc_num) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectConfSQL = "select dd.conf_num ,dd.acs_yn, m.name"
							+" from doc_detail dd, members m"
							+" where m.emp_num =dd.conf_num" 
							+" and dd.doc_num =?";
		List<DocDetailVO> doc_detaillist = new ArrayList<>();
		DocDetailVO doc_detailvo = null;	//doc_detail 데이터 담음
		
		try {
			con= MyConnection.getConnection();
			pstmt = con.prepareStatement(selectConfSQL);
			pstmt.setString(1, doc_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				doc_detailvo = new DocDetailVO();
				doc_detailvo.setConf_num(rs.getString("conf_num"));
				doc_detailvo.setAcs_yn(rs.getString("acs_yn"));
				Members mems = new Members(rs.getString("name"));
				doc_detailvo.setMembers(mems);
				doc_detaillist.add(doc_detailvo);	
			}
			
		}finally{
			MyConnection.close(rs,pstmt,con);
		}
			return doc_detaillist;
	}
		

	@Override
	public List<DocVO> selectGJWait(String emp_num, int page) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectGJWaitSQL = "select aa.*"
				+" from("
				+" select rownum r, mydocnum.doc_num, mydocnum.doc_kind, mydocnum.doc_title,"
				+" mydocnum.start_date, mydocnum.doc_state"
				+" from(select doc.* from"
				+" (select d.*, dd.conf_num"
				+" from document d, doc_detail dd"
				+" where d.doc_num = dd.doc_num) doc,"
				+" (select doc_num"
				+" from doc_detail"
				+" where conf_num = ?) myconf"
				+" where doc.doc_num = myconf.doc_num) mydocnum,"
				+" (select a.doc_num from"
				+" (select doc_num, sunbeon, acs_yn"
				+" from doc_detail) a,"
				+" (select doc_num, sunbeon, conf_num, acs_yn"
				+" from doc_detail"
				+" where conf_num = ?) b"
				+" where a.doc_num = b.doc_num and a.sunbeon = b.sunbeon-1"
				+" and a.acs_yn = 1 and b.acs_yn = 0"
				+" or b.sunbeon=0 and b.conf_num = ?) preconf"
				+" where mydocnum.doc_num = preconf.doc_num"
				+" and mydocnum.conf_num = ?) aa"
				+" where r between ? and ?";
		List<DocVO> doclist = new ArrayList<>(); //사이즈 변경 가능하며 null허용하는 arraylist
		DocVO docvo = null;	//doc 데이터 담음
	try {
		con= MyConnection.getConnection();
		pstmt = con.prepareStatement(selectGJWaitSQL);
		pstmt.setString(1, emp_num);
		pstmt.setString(2, emp_num);
		pstmt.setString(3, emp_num);
		pstmt.setString(4, emp_num);
		int cntPerPage=5;//1페이지별 5건씩 보여준다
		int endRow=cntPerPage * page;
		int startRow=endRow-cntPerPage+1; 			
		pstmt.setInt(5, startRow);	
		pstmt.setInt(6, endRow);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			docvo = new DocVO();
			docvo.setDoc_num(rs.getString("doc_num"));
			docvo.setDoc_kind(rs.getInt("doc_kind"));
			docvo.setDoc_title(rs.getString("doc_title"));
			docvo.setStart_date(rs.getString("start_date"));
			docvo.setDoc_state(rs.getString("doc_state"));
			doclist.add(docvo);	
		}
	}finally{
		MyConnection.close(rs,pstmt,con);
	}
		return doclist;
	}

	@Override
	public List<DocVO> selectIng(String emp_num) throws Exception { 
		//Main의 진행문서(내가올린거) table list 뿌려주는 select
		//매개변수 id 보여주는 갯수 제한 필요
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectIngSQL = "select d.start_date, d.doc_kind, d.doc_title, d.doc_num, d.doc_state, dk.doc_name"
							+" from document d, doc_kind dk"
							+" where d.doc_kind = dk.doc_kind"
							+" and d.emp_num = ?";
		
		List<DocVO> doclist = new ArrayList<>(); //사이즈 변경 가능하며 null허용하는 arraylist
		DocVO docvo = null;	//doc 데이터 담음
		
	try {
		con= MyConnection.getConnection();
		pstmt = con.prepareStatement(selectIngSQL);
		pstmt.setString(1, emp_num);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			docvo = new DocVO();
			docvo.setStart_date(rs.getString("start_date"));
			docvo.setDoc_kind(rs.getInt("doc_kind"));
			docvo.setDoc_title(rs.getString("doc_title"));
			docvo.setDoc_num(rs.getString("doc_num"));
			docvo.setDoc_state(rs.getString("doc_state"));
			DocKindVO dock = new DocKindVO(docvo.getDoc_kind(),rs.getString("doc_name"));
			docvo.setDoc_kindvo(dock);
			doclist.add(docvo);	
		}
		
		
		
	}finally{
		MyConnection.close(rs,pstmt,con);
	}
		return doclist;
	}
	
	@Override
	public List<DocVO> selectIng(String emp_num, int page) throws Exception {
		//사이드 메뉴 내가올린 기안 전부 보기 , 매개변수 id, page 
		Connection con = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;

		String selectIngSQL = "select b.*" 
						+" from ("
						+" select rownum r, d.start_date, d.doc_kind, d.doc_title, d.doc_num, d.doc_state, dk.doc_name"
						+" from document d, doc_kind dk"
						+" where d.doc_kind = dk.doc_kind"
						+" and d.emp_num = ?"
						+" order by doc_num desc) b"
						+" where r between ? and ?";
		
		List<DocVO> doclist = new ArrayList<>(); //사이즈 변경 가능하며 null허용하는 arraylist
		DocVO docvo = null;	//doc 데이터 담음
		List<DocKindVO> kindlist = new ArrayList<>(); //doc_kind arraylist
		
	try {
		con= MyConnection.getConnection();
		pstmt = con.prepareStatement(selectIngSQL);
		pstmt.setString(1, emp_num);
		int cntPerPage=5;//1페이지별 5건씩 보여준다
		int endRow=cntPerPage * page;
		int startRow=endRow-cntPerPage+1; 			
		pstmt.setInt(2, startRow);	
		pstmt.setInt(3, endRow);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			docvo = new DocVO();
			docvo.setStart_date(rs.getString("start_date"));
			docvo.setDoc_kind(rs.getInt("doc_kind"));
			docvo.setDoc_title(rs.getString("doc_title"));
			docvo.setDoc_num(rs.getString("doc_num"));
			docvo.setDoc_state(rs.getString("doc_state"));
			DocKindVO dock = new DocKindVO(docvo.getDoc_kind(),rs.getString("doc_name"));
			kindlist.add(dock);
			docvo.setDoc_kindvo(kindlist);
			doclist.add(docvo);	
		}
		
		
		
	}finally{
		MyConnection.close(rs,pstmt,con);
	}
		return doclist;
	}
	
	

	@Override
	public List<DocVO> selectOk(String emp_num) throws Exception { // 내가올린거 상태 완료인 문서 list로 뿌려주는 select

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
			String selectOkSQL = "select d.start_date, dk.doc_name, d.doc_title, d.doc_num, d.doc_state"
								+" from document d, doc_kind dk"
								+" where d.doc_kind = dk.doc_kind"
								+" and d.doc_state = 2"
								+" and d.emp_num = ?";
			List<DocVO> doclist2 = new ArrayList<>(); //사이즈 변경 가능하며 null허용하는 arraylist
			DocVO docvo2 = null;	//doc 데이터 담음
			DocKindVO dock2 = new DocKindVO();//dockind 데이터 담음
		try {
			con= MyConnection.getConnection();
			pstmt = con.prepareStatement(selectOkSQL);
			pstmt.setString(1, emp_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				docvo2 = new DocVO();
				docvo2.setStart_date(rs.getString("start_date"));
				docvo2.setDoc_title(rs.getString("doc_title"));
				docvo2.setDoc_num(rs.getString("doc_num"));
				docvo2.setDoc_state(rs.getString("doc_state"));
				dock2 = new DocKindVO(docvo2.getDoc_kind(),rs.getString("doc_name"));
				docvo2.setDoc_kindvo(dock2);
				doclist2.add(docvo2);	
			}
			
		}finally{
			MyConnection.close(rs,pstmt,con);
		}
		return doclist2;
	}	
	@Override
	public List<DocVO> selectOk(String emp_num, int page) throws Exception { // 내가올린거 상태 완료인 문서 list로 뿌려주는 select
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectOkSQL = "select d.start_date, dk.doc_name, d.doc_title, d.doc_num, d.doc_state, d.doc_kind"
				+" from document d, doc_kind dk"
				+" where d.doc_kind = dk.doc_kind"
				+" and d.doc_state = 2"
				+" and d.emp_num = ?";
		List<DocVO> doclist2 = new ArrayList<>(); //사이즈 변경 가능하며 null허용하는 arraylist
		DocVO docvo2 = null;	//doc 데이터 담음
		DocKindVO dock2 = new DocKindVO();//dockind 데이터 담음
		try {
			con= MyConnection.getConnection();
			pstmt = con.prepareStatement(selectOkSQL);
			pstmt.setString(1, emp_num);
			/*int cntPerPage=5;//1페이지별 5건씩 보여준다
			int endRow=cntPerPage * page;
			int startRow=endRow-cntPerPage+1; 			
			pstmt.setInt(1, startRow);	
			pstmt.setInt(2, endRow);
			위에 쿼리 수정해야 먹음*/
			rs = pstmt.executeQuery();
			while(rs.next()) {
				docvo2 = new DocVO();
				docvo2.setStart_date(rs.getString("start_date"));
				docvo2.setDoc_title(rs.getString("doc_title"));
				docvo2.setDoc_num(rs.getString("doc_num"));
				docvo2.setDoc_state(rs.getString("doc_state"));
				dock2 = new DocKindVO(docvo2.getDoc_kind(),rs.getString("doc_name"));
				docvo2.setDoc_kind(rs.getInt("doc_kind"));
				docvo2.setDoc_kindvo(dock2);
				doclist2.add(docvo2);	
			}
			
		}finally{
			MyConnection.close(rs,pstmt,con);
		}
		return doclist2;
	}	
		
			

	@Override
	public List<DocVO> selectExpected(String conf_num, int page) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectExpectedSQL = "select b.*"
				+" from("
				+" select  rownum r, d.doc_num, d.doc_title, d.doc_state, d.start_date, dk.doc_name, d.doc_kind"
				+" from document d, doc_detail dd, doc_kind dk"
				+" where d.doc_num = dd.doc_num"
				+" and d.doc_kind = dk.doc_kind"
				+" and conf_num = ?"
				+" and acs_yn = 0) b"
				+" where r between ? and ?";
		List<DocVO> doclist2 = new ArrayList<>(); //이부분부터 수정들어가야함 0525 오후 4:43
		DocVO docvo2 = null;	//doc 데이터 담음
		DocKindVO dock2 = new DocKindVO();//dockind 데이터 담음
		try {
			con= MyConnection.getConnection();
			pstmt = con.prepareStatement(selectExpectedSQL);
			pstmt.setString(1, conf_num);
			int cntPerPage=5;//1페이지별 5건씩 보여준다
			int endRow=cntPerPage * page;
			int startRow=endRow-cntPerPage+1; 			
			pstmt.setInt(2, startRow);	
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				docvo2 = new DocVO();
				docvo2.setDoc_num(rs.getString("doc_num"));
				docvo2.setDoc_title(rs.getString("doc_title"));
				docvo2.setDoc_state(rs.getString("doc_state"));
				docvo2.setStart_date(rs.getString("start_date"));
				dock2 = new DocKindVO(docvo2.getDoc_kind(),rs.getString("doc_name"));
				docvo2.setDoc_kind(rs.getInt("doc_kind"));
				docvo2.setDoc_kindvo(dock2);
				doclist2.add(docvo2);	
			}
			
		}finally{
			MyConnection.close(rs,pstmt,con);
		}
		return doclist2;

	}
	
	
	@Override
	public List<DocVO> selectGJOk(String conf_num, int page) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectGJOkSQL = "select b.*"
				+" from("
				+" select  rownum r, d.doc_num, d.doc_title, d.doc_state, d.start_date, dk.doc_name, d.doc_kind"
				+" from document d, doc_detail dd, doc_kind dk"
				+" where d.doc_num = dd.doc_num"
				+" and d.doc_kind = dk.doc_kind"
				+" and conf_num = ?"
				+" and acs_yn = 1) b"
				+" where r between ? and ?";
		List<DocVO> doclist2 = new ArrayList<>(); //이부분부터 수정들어가야함 0525 오후 4:43
		DocVO docvo2 = null;	//doc 데이터 담음
		DocKindVO dock2 = new DocKindVO();//dockind 데이터 담음
		try {
			con= MyConnection.getConnection();
			pstmt = con.prepareStatement(selectGJOkSQL);
			pstmt.setString(1, conf_num);
			int cntPerPage=5;//1페이지별 5건씩 보여준다
			int endRow=cntPerPage * page;
			int startRow=endRow-cntPerPage+1; 			
			pstmt.setInt(2, startRow);	
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				docvo2 = new DocVO();
				docvo2.setDoc_num(rs.getString("doc_num"));
				docvo2.setDoc_title(rs.getString("doc_title"));
				docvo2.setDoc_state(rs.getString("doc_state"));
				docvo2.setStart_date(rs.getString("start_date"));
				dock2 = new DocKindVO(docvo2.getDoc_kind(),rs.getString("doc_name"));
				docvo2.setDoc_kind(rs.getInt("doc_kind"));
				docvo2.setDoc_kindvo(dock2);
				doclist2.add(docvo2);	
			}
			
		}finally{
			MyConnection.close(rs,pstmt,con);
		}
		return doclist2;
	}
	
	
	public static void main(String[] args) {
		DocDAOOracle test = new DocDAOOracle();
		try {
			List<DocVO> list = test.selectIng("1");	
			System.out.println("리스트사이즈:"+list.size());
			List<DocVO> list2 = test.selectOk("kim");
			System.out.println("ㅁㅁㅁㅁ"+list2.size());
			List<DocVO> list3 = test.selectIng("1",1);
			System.out.println("3333aa"+list3.size());
			List<DocVO> list4 = test.selectOk("kim",1);
			System.out.println("44aaokokok"+list4.size());
			List<DocVO> list5 = test.selectExpected("3", 1);
			System.out.println(list5.size());
			List<DocVO> list6 = test.selectGJOk("3", 1);
			System.out.println("list6ggggg:"+list6);
			DocVO list7 = test.selectAll("1805-0001");
			System.out.println(list7);
			System.out.println("--------------");
			List<DocDetailVO> list8 = test.selectConf("1805-0001");
			System.out.println("list8="+list8);
			List<DocVO> list9 = test.selectGJWait("4", 1);
			System.out.println(list9);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	
	
	
}
