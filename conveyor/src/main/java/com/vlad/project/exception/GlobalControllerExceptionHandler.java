package com.vlad.project.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerExceptionHandler {

    private final ProblemDetailFactory problemDetailFactory;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> showMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                    HttpServletRequest request) {
        log.error("Произошло исключение -> {}; вызвался метод -> showMethodArgumentNotValid", ex.getClass());
        var fieldError = ex.getBindingResult().getFieldError();

        var field = fieldError != null ? fieldError.getField() : "unknown";
        var message = fieldError != null ? fieldError.getDefaultMessage() : "Некорректный запрос";

        var problemDetail = problemDetailFactory.createForValidationException(
                HttpStatus.BAD_REQUEST,
                message,
                field,
                request
        );

        return ResponseEntity.badRequest().body(problemDetail);
    }

    @ExceptionHandler(ScoringException.class)
    public ResponseEntity<ProblemDetail> scoringExceptionInCalculationMethod(ScoringException ex,
                                                                             HttpServletRequest request) {
        log.error("Произошло исключение -> {}; вызвался метод -> scoringExceptionInCalculationApi", ex.getClass());

        String field = ex.getField() != null ? ex.getField() : "unknown";
        var message = ex.getMessage() != null ? ex.getMessage() : "Некорректный запрос";
        var problemDetail = problemDetailFactory.createForValidationException(
                HttpStatus.BAD_REQUEST,
                message,
                field,
                request
        );

        return ResponseEntity.badRequest().body(problemDetail);
    }
}