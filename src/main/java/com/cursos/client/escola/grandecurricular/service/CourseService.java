package com.cursos.client.escola.grandecurricular.service;

import com.cursos.client.escola.grandecurricular.dto.CourseDTO;
import com.cursos.client.escola.grandecurricular.entity.CourseEntity;
import com.cursos.client.escola.grandecurricular.exception.CourseException;
import com.cursos.client.escola.grandecurricular.repository.ICourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    static final String ERROR_SERVER_MESSAGE = "an internal server error occurred";

    @Autowired
    private ICourseRepository iCourseRepository;

    @Override
    public Boolean register(CourseDTO course) {
        try {
            ModelMapper mapper = new ModelMapper();
            CourseEntity courseEntity = mapper.map(course, CourseEntity.class);

            this.iCourseRepository.save(courseEntity);
            return true;
        } catch (CourseException courseException) {
            throw courseException;
        } catch (Exception e) {
            throw new CourseException(ERROR_SERVER_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean update(CourseDTO course) {
        try {
            Optional<CourseEntity> optionalCourseEntity = this.iCourseRepository.findById(course.getId());

            if (optionalCourseEntity.isPresent()) {
                ModelMapper mapper = new ModelMapper();

                CourseEntity courseEntity = mapper.map(course, CourseEntity.class);

                this.iCourseRepository.save(courseEntity);

                return true;
            }
            return false;
        } catch (CourseException courseException) {
            throw courseException;
        } catch (Exception e) {
            throw new CourseException(ERROR_SERVER_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CourseEntity getOne(Long id) {
        try {
            Optional<CourseEntity> optional = this.iCourseRepository.findById(id);
            if (optional.isPresent())
                return optional.get();
            throw new CourseException("course does not found", HttpStatus.NOT_FOUND);
        } catch (CourseException courseException) {
            throw courseException;
        } catch (Exception e) {
            throw new CourseException(ERROR_SERVER_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean delete(Long id) {
        try {
            this.getOne(id);
            this.iCourseRepository.deleteById(id);
            return true;
        } catch (CourseException courseException) {
            throw courseException;
        } catch (Exception e) {
            throw new CourseException(ERROR_SERVER_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CourseEntity> getAll() {
        try {
            return this.iCourseRepository.findAll();
        } catch (CourseException courseException) {
            throw courseException;
        } catch (Exception e) {
            throw new CourseException(ERROR_SERVER_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
