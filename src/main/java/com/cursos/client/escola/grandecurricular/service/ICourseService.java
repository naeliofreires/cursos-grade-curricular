package com.cursos.client.escola.grandecurricular.service;

import com.cursos.client.escola.grandecurricular.dto.CourseDTO;
import com.cursos.client.escola.grandecurricular.entity.CourseEntity;

import java.util.List;

public interface ICourseService {

    public Boolean register(final CourseDTO course);

    public Boolean update(final CourseDTO course);

    public Boolean delete(final Long id);

    public CourseEntity getOne(final Long id);

    public List<CourseEntity> getAll();
}
