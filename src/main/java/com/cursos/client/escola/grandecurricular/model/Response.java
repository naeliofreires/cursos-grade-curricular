package com.cursos.client.escola.grandecurricular.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = true)
public class Response<T> extends RepresentationModel<Response<T>> {
    private int statusCode;
    private T data;
}
