package com.example.livros.handler;

import com.example.livros.exception.DadosAusentesException;
import com.example.livros.exception.ErrorResponse;
import com.example.livros.exception.ItemNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ItemNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handlerItemNaoEncontrado(ItemNaoEncontradoException ine) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .mensagem(ine.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(DadosAusentesException.class)
    public ResponseEntity<ErrorResponse> handlerDadosAusentes(DadosAusentesException dae) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .mensagem(dae.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerException(DadosAusentesException dae) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .mensagem(dae.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
