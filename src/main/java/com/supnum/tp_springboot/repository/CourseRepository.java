package com.supnum.tp_springboot.repository;

import com.supnum.tp_springboot.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}