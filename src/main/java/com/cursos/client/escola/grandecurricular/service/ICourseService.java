package com.cursos.client.escola.grandecurricular.service;

import com.cursos.client.escola.grandecurricular.dto.CourseDTO;

import java.util.List;

public interface ICourseService {

    public Boolean register(final CourseDTO course);

    public Boolean update(final CourseDTO course);

    public Boolean delete(final Long id);

    public CourseDTO getOne(final Long id);

    public List<CourseDTO> getAll();
}
