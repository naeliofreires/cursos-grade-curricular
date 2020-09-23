package com.cursos.client.escola.grandecurricular.service;

import com.cursos.client.escola.grandecurricular.controller.CourseController;
import com.cursos.client.escola.grandecurricular.dto.CourseDTO;
import com.cursos.client.escola.grandecurricular.entity.CourseEntity;
import com.cursos.client.escola.grandecurricular.exception.CourseException;
import com.cursos.client.escola.grandecurricular.repository.ICourseRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// When it's enabled. You don't need of [value = KEY_CACHE] in all annotations for the cache
// @CacheConfig(cacheNames = {"course" })
@Service
public class CourseService implements ICourseService {

    static final String KEY_CACHE = "course";
    static final String ERROR_SERVER_MESSAGE = "an internal server error occurred";

    private final ModelMapper mapper;
    private final ICourseRepository iCourseRepository;

    @Autowired
    CourseService(ICourseRepository iCourseRepository) {
        this.mapper = new ModelMapper();
        this.iCourseRepository = iCourseRepository;
    }

    @Override
    public Boolean register(CourseDTO course) {
        try {
            CourseEntity courseEntity = this.mapper.map(course, CourseEntity.class);

            this.iCourseRepository.save(courseEntity);
            return Boolean.TRUE;
        } catch (CourseException courseException) {
            throw courseException;
        } catch (Exception e) {
            throw new CourseException(ERROR_SERVER_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @Caching(evict = {
    //            @CacheEvict(value = "course", key = "#course.id")
    //            @CacheEvict(value = "other", key = "#other.id")
    //    })
    @CacheEvict(value = KEY_CACHE, key = "#course.id") // always clean the cache
    @Override
    public Boolean update(CourseDTO course) {
        try {
            Optional<CourseEntity> optionalCourseEntity = this.iCourseRepository.findById(course.getId());

            if (optionalCourseEntity.isPresent()) {

                CourseEntity courseEntity = this.mapper.map(course, CourseEntity.class);

                this.iCourseRepository.save(courseEntity);

                return Boolean.TRUE;
            }
            throw new CourseException(ERROR_SERVER_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CourseException courseException) {
            throw courseException;
        } catch (Exception e) {
            throw new CourseException(ERROR_SERVER_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Cacheable(value = KEY_CACHE, key = "#id")
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
            return Boolean.TRUE;
        } catch (CourseException courseException) {
            throw courseException;
        } catch (Exception e) {
            throw new CourseException(ERROR_SERVER_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @CachePut always will check if there are any changes on the result
    @CachePut(value = KEY_CACHE, unless = "#result.size()<3")
    @Override
    public List<CourseDTO> getAll() {
        try {
            /**
             * applying DTO model
             */
            List<CourseDTO> courseDTOList =
                    this.mapper
                            .map(this.iCourseRepository.findAll(), new TypeToken<List<CourseDTO>>() {
                            }.getType());

            /**
             * applying hyper media [HATEOAS]
             */
            courseDTOList.forEach(courseDTO -> {
                courseDTO
                        .add(WebMvcLinkBuilder
                                .linkTo(WebMvcLinkBuilder
                                        .methodOn(CourseController.class)
                                        .getOne(courseDTO.getId())).withSelfRel());
            });

            return courseDTOList;
        } catch (CourseException courseException) {
            throw courseException;
        } catch (Exception e) {
            throw new CourseException(ERROR_SERVER_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
