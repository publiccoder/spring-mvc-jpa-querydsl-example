package com.test.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.service.TestService;
import com.test.service.UserService;

@RestController
@RequestMapping(value = "/")
public class IndexController {

	@Autowired
	private UserService userService;
	@Autowired
	private TestService testService;

	@RequestMapping
	public Object index(HttpServletRequest request) {
		Enumeration<String> headerNames = request.getHeaderNames();
		Map<String, Object> headers = new HashMap<>();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			headers.put(headerName, request.getHeader(headerName));
		}
		return headers;
	}

	@RequestMapping(value = "crud")
	public Object crud(@RequestParam(required = false) Integer page) {
		page = page == null ? 0 : (page > 0 ? page - 1 : page);
		return userService.crud(page);
	}

	@RequestMapping(value = "test")
	public Object test() {
		return testService.test();
	}

}
