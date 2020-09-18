package com.cursos.client.escola.grandecurricular.repository;

import com.cursos.client.escola.grandecurricular.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends JpaRepository<CourseEntity, Long> {
}
