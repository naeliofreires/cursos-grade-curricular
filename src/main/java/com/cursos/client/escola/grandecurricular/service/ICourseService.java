package com.cursos.client.escola.grandecurricular.service;

import com.cursos.client.escola.grandecurricular.entity.CourseEntity;

import java.util.List;

public interface ICourseService {

    public Boolean register(final CourseEntity course);

    public Boolean update(final CourseEntity course);

    public Boolean delete(final Long id);

    public CourseEntity getOne(final Long id);

    public List<CourseEntity> getAll();
}
