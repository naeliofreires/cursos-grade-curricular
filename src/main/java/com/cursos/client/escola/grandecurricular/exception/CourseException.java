package com.cursos.client.escola.grandecurricular.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CourseException extends RuntimeException {

    private final HttpStatus httpStatus;

    public CourseException(final String msg, final HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}
