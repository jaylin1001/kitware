package com.kitware.schedule.service;

import java.util.List;

import com.kitware.schedule.vo.Schedule;
import com.kitware.schedule.dao.SchCodeDAO;
import com.kitware.schedule.dao.SchCodeDAOOracle;

public class SchCodeService {
	static private SchCodeService service;
	private SchCodeDAO dao = new SchCodeDAOOracle();
	
	
	
	public SchCodeService() {
		
	}
	public static SchCodeService getInstance() {
		if(service==null) {
			service = new SchCodeService();
		}
		return service;
	}
	
	public List<Schedule> findSchPersonalToday(String id, String date) throws Exception{
		return dao.selectPersonalToday(id, date);
	}
	
	public List<Schedule> findSchPersonal(String id) throws Exception{
		return dao.selectPersonal(id);
	}
	
	public List<Schedule> findSchDept(String id) throws Exception{
		return dao.selectDepartment(id);
	}
	
	public List<Schedule> findSchCompany (String id)throws Exception{
		return dao.selectCompany(id);
	}

	public List<Schedule> findSchTotal(String id) throws Exception {
		
		return dao.selectTotal(id);
	}
}
