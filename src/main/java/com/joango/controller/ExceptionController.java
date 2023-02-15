package com.joango.controller;

import com.joango.exception.EntityNotFoundException;
import com.joango.exception.OperationErrorException;
import com.joango.utils.HtmlUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView entityNotFoundExceptionHandling(EntityNotFoundException ex) {
        return HtmlUtils.createErrorHandlingTemplate(ex, "error");
    }

    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(OperationErrorException.class)
    public ModelAndView operationErrorException(OperationErrorException ex) {
        return HtmlUtils.createErrorHandlingTemplate(ex, "operationError");
    }
}
