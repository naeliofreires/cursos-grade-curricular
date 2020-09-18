package com.cursos.client.escola.grandecurricular.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_course")
@Data
@NoArgsConstructor
public class CourseEntity implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "name")
    private String name;

    @Column(name = "hours")
    private int hours;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "code")
    private String code;

    @Column(name = "frequency")
    private int frequency;

}
