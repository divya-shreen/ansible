package com.atomi.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public String handleEmployeeNotFound(EmployeeNotFoundException ex, Model model) {
		model.addAttribute("errorTitle", "Employee Not Found");
		model.addAttribute("errorMessage", ex.getMessage());
		return "Error.html";
	}

	@ExceptionHandler(NoEmployeesFoundException.class)
	public String handleNoEmployeesFound(NoEmployeesFoundException ex, Model model) {
		model.addAttribute("m", ex.getMessage());
		return "Main.html";
	}

	@ExceptionHandler(ServiceException.class)
	public String handleServiceException(ServiceException ex, Model model) {
		model.addAttribute("errorTitle", "Service Error");
		model.addAttribute("errorMessage", ex.getMessage());
		return "Error.html";
	}

	@ExceptionHandler(Exception.class)
	public String handleGeneralException(Exception ex, Model model) {
		model.addAttribute("errorTitle", "Something Went Wrong");
		model.addAttribute("errorMessage", ex.getMessage());
		return "Error.html";
	}
}
