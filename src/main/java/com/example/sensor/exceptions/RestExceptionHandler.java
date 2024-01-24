package com.example.sensor.exceptions;

import com.example.sensor.model.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Component
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logError(ex);
        return ResponseEntity
                .status(status)
                .body(ErrorDto.builder()
                        .error(String.format("Http method %s not supported. Allowed methods are: %s", ex.getMethod(), String.join(", ", ex.getSupportedHttpMethods().stream().map(HttpMethod::name).toList())))
                        .build());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logError(ex);
        return ResponseEntity
                .status(status)
                .body(ErrorDto.builder()
                        .error(String.format("Media type %s not supported. Allowed media types are: %s", ex.getContentType(), String.join(", ", ex.getSupportedMediaTypes().stream().map(MediaType::getType).toList())))
                        .build());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logError(ex);
        return ResponseEntity
                .status(status)
                .body(ErrorDto.builder()
                        .error(String.format("Media type not acceptable. Supported media types are: %s", String.join(", ", ex.getSupportedMediaTypes().stream().map(MediaType::getType).toList())))
                        .build());
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logError(ex);
        return ResponseEntity
                .status(status)
                .body(ErrorDto.builder()
                        .error(String.format("Missing path variable: %s", ex.getVariableName()))
                        .build());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logError(ex);
        return ResponseEntity
                .status(status)
                .body(ErrorDto.builder()
                        .error(String.format("Missing parameter [ %s ] in request of type [ %s ]", ex.getParameterName(), ex.getParameterType()))
                        .build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logError(ex);
        return ResponseEntity
                .status(status)
                .body(ex.getFieldErrors().stream()
                        .map(f -> ErrorDto.builder()
                                .error(String.format("Error on field [ %s ], rejected value [ %s ]", f.getField(), f.getRejectedValue()))
                                .build())
                        .toList());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logError(ex);
        return ResponseEntity
                .status(status)
                .body(ErrorDto.builder()
                        .error(String.format("Missing request part [ %s ]", ex.getRequestPartName()))
                        .build());
    }

/*    @ExceptionHandler(Exception.class)
    public ErrorDto handleAll(Exception e) {
        logError(e);
        return ErrorDto.builder()
                .error(e.getMessage())
                .build();
    } */

    private void logError(Exception e) {
        log.error("Exception thrown! ", e);
    }

}
