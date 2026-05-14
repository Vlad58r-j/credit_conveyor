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