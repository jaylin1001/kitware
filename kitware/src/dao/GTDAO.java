package dao;

import vo.Gunte;

public interface GTDAO {
	void ininsert(Gunte g) throws Exception;
	/*Gunte selectById(String id) throws Exception;*/
	void outupdate(Gunte g) throws Exception;
}
