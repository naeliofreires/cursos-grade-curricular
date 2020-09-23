package com.cursos.client.escola.grandecurricular.controller;

import com.cursos.client.escola.grandecurricular.dto.CourseDTO;
import com.cursos.client.escola.grandecurricular.entity.CourseEntity;
import com.cursos.client.escola.grandecurricular.model.Response;
import com.cursos.client.escola.grandecurricular.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private static final String DELETE = "DELETE";

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<Response<List<CourseDTO>>> getAll() {
        Response<List<CourseDTO>> response = new Response();

        response.setStatusCode(HttpStatus.OK.value());
        response.setData(this.courseService.getAll());

        response
                .add(WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(CourseController.class)
                                .getAll())
                        .withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<CourseEntity>> getOne(@PathVariable Long id) {
        Response<CourseEntity> response = new Response<>();

        response.setStatusCode(HttpStatus.OK.value());
        response.setData(this.courseService.getOne(id));

        response
                .add(WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(CourseController.class)
                                .getOne(id))
                        .withSelfRel());
        response
                .add(WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(CourseController.class)
                                .delete(id))
                        .withRel(DELETE));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Boolean> register(@Valid @RequestBody CourseDTO course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.courseService.register(course));
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@Valid @RequestBody CourseDTO course) {
        return ResponseEntity.status(HttpStatus.OK).body(this.courseService.update(course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.courseService.delete(id));
    }
}
