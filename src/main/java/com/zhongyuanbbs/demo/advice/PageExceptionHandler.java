package com.zhongyuanbbs.demo.advice;

import com.zhongyuanbbs.demo.exception.CustiomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class PageExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handleController(HttpServletRequest request, Throwable ex, Model model) {
        HttpStatus status = getStatus(request);
        if(ex instanceof CustiomizeException) {
            model.addAttribute("message", ex.getMessage());
        }else {
            model.addAttribute("message", "服务器走丢了，请重新访问。。");
        }
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
