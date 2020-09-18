package com.cursos.client.escola.grandecurricular.service;

import com.cursos.client.escola.grandecurricular.entity.CourseEntity;
import com.cursos.client.escola.grandecurricular.repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private ICourseRepository iCourseRepository;

    @Override
    public Boolean register(CourseEntity course) {
        try {
            this.iCourseRepository.save(course);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(CourseEntity course) {
        try {
            Optional<CourseEntity> entityOptional = this.iCourseRepository.findById(course.getId());

            if (entityOptional.isPresent()) {
                CourseEntity founded = entityOptional.get();
                founded.setName(course.getName());
                founded.setHours(course.getHours());
                founded.setCode(course.getCode());
                founded.setFrequency(course.getFrequency());

                this.iCourseRepository.save(founded);

                return true;
            }

            return false;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(Long id) {
        try {
            Optional optional = this.iCourseRepository.findById(id);

            if (optional.isPresent()) {
                this.iCourseRepository.deleteById(id);

                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public CourseEntity getOne(Long id) {
        Optional optional = this.iCourseRepository.findById(id);
        if (optional.isPresent())
            return (CourseEntity) optional.get();
        return null;
    }

    @Override
    public List<CourseEntity> getAll() {
        List<CourseEntity> allCourses = this.iCourseRepository.findAll();
        return allCourses;
    }
}
