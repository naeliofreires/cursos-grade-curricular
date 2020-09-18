package com.cursos.client.escola.grandecurricular.controller;

import com.cursos.client.escola.grandecurricular.entity.CourseEntity;
import com.cursos.client.escola.grandecurricular.repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseRepository iCourseRepository;

    @GetMapping
    public ResponseEntity<List<CourseEntity>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(iCourseRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseEntity> getOne(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.iCourseRepository.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody CourseEntity course) {
        try {
            this.iCourseRepository.save(course);
            return ResponseEntity.status(HttpStatus.OK).body("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body("Sorry, an error occurred");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody CourseEntity subject) {
        try {
            Optional entityOptional = this.iCourseRepository.findById(id);

            if (entityOptional.isPresent()) {
                CourseEntity founded = (CourseEntity) entityOptional.get();
                founded.setName(subject.getName());
                founded.setHours(subject.getHours());
                founded.setCode(subject.getCode());
                founded.setFrequency(subject.getFrequency());


                this.iCourseRepository.save(founded);

                return ResponseEntity.status(HttpStatus.OK).body("Updated");
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sorry, is not possible right now");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try {
            Optional optional = this.iCourseRepository.findById(id);

            if(optional.isPresent()){
                this.iCourseRepository.deleteById(id);

                return ResponseEntity.status(HttpStatus.OK).body("Course was deleted");
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course was not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
