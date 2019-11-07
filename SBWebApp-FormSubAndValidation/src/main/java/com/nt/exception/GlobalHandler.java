package com.nt.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
@ControllerAdvice
public class GlobalHandler {
	
	@ExceptionHandler(value= {NullPointerException.class,NumberFormatException.class})
	public String nullPExceptionCustom(Model model) {
		model.addAttribute("errMsg","Internal Problem");
		return "errorPage";
	}
	
/*	@ExceptionHandler(value=NumberFormatException.class)
	public String NFExceptionCustom(Model model) {
		model.addAttribute("errMsg","Internal Problem ");
		return "errorPage";
	}*/

	

}
