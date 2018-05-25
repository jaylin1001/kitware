/**
 * 
 */
package service;

import dao.GTDAO;
import dao.GTDAOOracle;
import vo.Gunte;

/**
 * @author Administrator
 *
 */
public class GTService {
	private GTDAO dao = new GTDAOOracle();
	
	public void attendance(Gunte g) throws Exception{
		dao.inupdate(g);
		dao.outupdate(g);
	}
}
