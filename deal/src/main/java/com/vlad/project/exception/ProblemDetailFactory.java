package com.vlad.project.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class ProblemDetailFactory {

    public ProblemDetail detailFactory(
            HttpStatus status,
            String message,
            HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, message);
        problemDetail.setTitle(status.getReasonPhrase());
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        return problemDetail;
    }
}
