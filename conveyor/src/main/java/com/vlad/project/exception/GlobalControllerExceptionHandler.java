package com.vlad.project.exception;

import io.swagger.v3.oas.annotations.tags.Tag;
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

        var field = fieldError != null ? fieldError.getField() : "unknow";
        var message = fieldError != null ? fieldError.getDefaultMessage() : "Некорректный запрос";

        var problemDetail = problemDetailFactory.createForValidationException(
                HttpStatus.BAD_REQUEST,
                message,
                field,
                request
        );

        return ResponseEntity.badRequest().body(problemDetail);
    }

    //"type": "https://example.com/problem/invalid-request",
    //  "title": "Invalid request",
    //  "status": 400,
    //  "detail": "Amount must be greater than 10000",
    //  "instance": "/loans/apply"


//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex,
//            HttpServletRequest request
//    ) {
//        FieldError fieldError = ex.getBindingResult().getFieldError();
//
//        String field = fieldError != null ? fieldError.getField() : "unknown";
//        String message = fieldError != null ? fieldError.getDefaultMessage() : "Некорректный запрос";
//
//        if ("organizations".equals(field)) {
//            Object rejectedValue = fieldError.getRejectedValue();
//            Integer size = null;
//
//            if (rejectedValue instanceof Collection<?>) {
//                size = ((Collection<?>) rejectedValue).size();
//            }
//
//            log.warn(
//                    "Validation error: method={}, uri={}, field={}, size={}, message={}",
//                    request.getMethod(),
//                    request.getRequestURI(),
//                    field,
//                    size,
//                    message
//            );
//        } else {
//            log.warn(
//                    "Validation error: method={}, uri={}, field={}, message={}",
//                    request.getMethod(),
//                    request.getRequestURI(),
//                    field,
//                    message
//            );
//        }
//
//        ProblemDetail problemDetail = problemDetailFactory.createValidationProblem(
//                "VALIDATION_ERROR",
//                message,
//                field,
//                request
//        );
//
//        return ResponseEntity.badRequest().body(problemDetail);
//    }
}