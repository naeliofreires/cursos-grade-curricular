package com.cursos.client.escola.grandecurricular.controller;

import com.cursos.client.escola.grandecurricular.entity.CourseEntity;
import com.cursos.client.escola.grandecurricular.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseEntity>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.courseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseEntity> getOne(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.courseService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<Boolean> register(@RequestBody CourseEntity course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.courseService.register(course));
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody CourseEntity course) {
        return ResponseEntity.status(HttpStatus.OK).body(this.courseService.update(course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.courseService.delete(id));
    }
}
