package com.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.sentry.Sentry;

@RestControllerAdvice
public class MyExceptionHandler {

	@ExceptionHandler
	public Object handleException(Throwable t) {
		t.printStackTrace();

		Sentry.capture(t);

		Map<String, Object> result = new HashMap<>();
		result.put("code", 500);
		result.put("message", t.getMessage());
		
		return result;
	}

}
