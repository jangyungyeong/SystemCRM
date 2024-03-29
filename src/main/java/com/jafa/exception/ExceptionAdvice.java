package com.jafa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice
@Log4j
public class ExceptionAdvice {

	// 일반적예외처리
	@ExceptionHandler(Exception.class)
	public String exception(Exception ex, Model model) {
		log.error("...............EXCEPTION............" + ex.getMessage());
		model.addAttribute("exception", ex);
		log.error(model);
		return "error_page";
	}
	
	// 응답상태코드가 404오류일때의 예외처리
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404() {
		log.info(".........NO HANDLER.........");
		return "custom404";
	}
	
	// 응답상태코드가 403오류일때의 예외처리
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public String handle403() {
		log.info(".........FORBIDDEN.........");
		return "accessError";
	}
}
