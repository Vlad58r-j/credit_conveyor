package com.vlad.project.exception;

import lombok.Getter;

@Getter
public class ScoringException extends RuntimeException {

    private final String field;

    public ScoringException(String message, String field) {
        super(message);
        this.field = field;
    }

}
