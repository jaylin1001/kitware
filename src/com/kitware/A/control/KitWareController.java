package com.kitware.A.control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.authorization.service.DocManipulService;
import com.kitware.authorization.service.DocSelectService;
import com.kitware.board.service.BoardService;
import com.kitware.guntae.service.GTService;
import com.kitware.member.service.MemberService;
import com.kitware.schedule.service.SchCodeService;
import com.kitware.schedule.service.SchDMLService;

/**
 * Servlet implementation class FrontController
 */
public class KitWareController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SchCodeService schcodeService = SchCodeService.getInstance();
	private SchDMLService schdmlService = SchDMLService.getInstance();
	private DocManipulService docmanipulService = DocManipulService.getInstance();
	private DocSelectService docselectService = DocSelectService.getInstance();
	private GTService gtService = GTService.getInstance();
	private MemberService memberService = MemberService.getInstance();
	private BoardService boardService = BoardService.getInstance();
	private Properties env;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		env = new Properties();
		ServletContext application = getServletContext();
		String filePath = application.getRealPath(this.getInitParameter("propLocation"));
		FileInputStream fis;
		try {
			fis = new FileInputStream(filePath);
			env.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		String forwardURL = "";
		// my.properties 파일을 ㅇㄺ어서 Properties 객체화

		String key = path;
		String className = env.getProperty(key);
		System.out.println(className);
		// 클래스이름(eg:ProductListContoller)으로,
		// ProductListControllerclass을 찾아 JVM으로 로드!
		try {
			Class clazz = Class.forName(className);
			// Constructor[] constructors = clazz.getDeclaredConstructors();
			Object obj = clazz.newInstance();
			Method[] methods = clazz.getDeclaredMethods();
			String packPath = "com.kitware.";
			for (Method m : methods) {
				for (Parameter parameter : m.getParameters()) {
					Class parameterType = parameter.getType();
					if ((packPath + "schedule.service.SchCodeService").equals(parameterType.getName())) {
						m.invoke(obj, schcodeService);
						break;
					} else if ((packPath + "schedule.service.SchDMLService").equals(parameterType.getName())) {
						m.invoke(obj, schdmlService);
						break;
					} else if ((packPath + "authorization.service.DocManipulService").equals(parameterType.getName())) {
						m.invoke(obj, docmanipulService);
						break;
					} else if ((packPath + "authorization.service.DocSelectService").equals(parameterType.getName())) {
						m.invoke(obj, docselectService);
						break;
					} else if ((packPath + "guntae.service.GTService").equals(parameterType.getName())) {
						m.invoke(obj, gtService);
						break;
					} else if ((packPath + "board.service.BoardService").equals(parameterType.getName())) {
						m.invoke(obj, boardService);
						break;
					} else if ((packPath + "member.service.MemberService").equals(parameterType.getName())) {
						m.invoke(obj, memberService);
						break;
					}
				}
			}
			for (Method m : methods) {
				if ("execute".equals(m.getName())) {
					forwardURL = (String) m.invoke(obj, request, response);
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		// forwardURL = c.execute(request, response);

		if (forwardURL.contains("redirect:")) {
			String redirectURL = forwardURL.substring(forwardURL.indexOf(":") + 1);
			if (redirectURL.equals("")) {
				redirectURL = "/";
			}
			System.out.println(redirectURL);
			response.sendRedirect(redirectURL);
			return;
			// response.sendRedirect(request.getContextPath());
		} else if (forwardURL == "") {
			return;
		} else {
			System.out.println(forwardURL);
			RequestDispatcher rd = request.getRequestDispatcher(forwardURL);
			rd.forward(request, response);
		}

	}
}
