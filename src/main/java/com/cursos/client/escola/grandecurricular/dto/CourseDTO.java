package com.cursos.client.escola.grandecurricular.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CourseDTO {

    private Long id;

    @NotBlank(message = "name can't be null")
    private String name;

    @Min(value = 34, message = "the min value is 34")
    @Max(value = 100, message = "the max value is 100")
    private int hours;

    @NotBlank(message = "name can't be null")
    private String code;

    @Min(value = 1, message = "allowed one time for the year")
    @Max(value = 2, message = "allowed two time for the year")
    private int frequency;
}
