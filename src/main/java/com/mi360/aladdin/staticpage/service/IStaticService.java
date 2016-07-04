package com.mi360.aladdin.staticpage.service;

public interface IStaticService {
	void staticPageToPath(String requestId, String sourceUrl,String fileName);
	void staticPagePC(String requestId, String sourceUrl, String fileName);
}
