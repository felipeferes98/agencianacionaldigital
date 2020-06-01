package com.testagenciand.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    public static final String PAGINA_ERROR_500= "error/500";

    @ExceptionHandler(Exception.class)
    public String mostrarPaginaError(){
        return PAGINA_ERROR_500;
    }

}
