package com.cursos.client.escola.grandecurricular.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * @Builder lets you automatically produce the code required to have your class be instantiable with code such as:
 * Person.builder()
 *       .name("Adam Savage")
 *       .city("San Francisco")
 *       .job("Dev")
 *       job("Unchained Reaction")
 *       .build();
 */
@Builder
@Getter
public class ErrorMapResponse {

    private int httpStatus;
    private Long timeStamp;
    private Map<String, String> errors;


}
