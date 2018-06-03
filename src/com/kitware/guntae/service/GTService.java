package com.kitware.guntae.service;

public class GTService {
	static private GTService service;

	public GTService() {

	}

	public static GTService getInstance() {
		if (service == null) {
			service = new GTService();
		}
		return service;
	}
}
