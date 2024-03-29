package com.Muchanga.PesquisaDeLivros.user.infra;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Muchanga.PesquisaDeLivros.user.dtos.ExceptionDTO;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Email Ja Cadastrado", "500");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }
}
