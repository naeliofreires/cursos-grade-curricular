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
        CourseEntity entity = this.courseService.getOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(entity);
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody CourseEntity course) {
        Boolean result = this.courseService.register(course);

        return result ?
                ResponseEntity.status(HttpStatus.CREATED).body("Course was saved") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course was not saved");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody CourseEntity course) {
        Boolean result = this.courseService.update(course);

        return result ?
                ResponseEntity.status(HttpStatus.OK).body("Course was updated") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course was not updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Boolean result = this.courseService.delete(id);

        return result ?
                ResponseEntity.status(HttpStatus.OK).body("Course was deleted") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course was not deleted");
    }
}
