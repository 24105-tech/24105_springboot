package com.supnum.tp_springboot.repository;

import com.supnum.tp_springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);
}