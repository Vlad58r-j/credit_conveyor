package com.vlad.project.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class ProblemDetailFactory {

    public ProblemDetail createForValidationException(
            HttpStatus status,
            String message,
            String field,
            HttpServletRequest request) {

        var problemDetail = ProblemDetail.forStatusAndDetail(status, message);

        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setTitle(status.getReasonPhrase());
        problemDetail.setProperty("field", field);

        return problemDetail;
    }


}
//    public ProblemDetail create(
//            HttpStatus status,
//            String code,
//            String detail,
//            HttpServletRequest request
//    ) {
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
//        problemDetail.setTitle(status.getReasonPhrase());
//
//        if (request != null) {
//            problemDetail.setInstance(URI.create(request.getRequestURI()));
//        }
//
//        problemDetail.setProperty("code", code);
//        return problemDetail;
//    }
//
//    public ProblemDetail createValidationProblem(
//            String code,
//            String detail,
//            String field,
//            HttpServletRequest request
//    ) {
//        ProblemDetail problemDetail = create(HttpStatus.BAD_REQUEST, code, detail, request);
//        if (field != null) {
//            problemDetail.setProperty("field", field);
//        }
//        return problemDetail;
//    }