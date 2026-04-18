package com.supnum.tp_springboot.controller;

import com.supnum.tp_springboot.StudentDTO;
import com.supnum.tp_springboot.entity.Course;
import com.supnum.tp_springboot.entity.Student;
import com.supnum.tp_springboot.repository.CourseRepository;
import com.supnum.tp_springboot.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @PostMapping
    public Student create(@RequestBody @Valid StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        return studentRepository.save(student);
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouve : " + id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody @Valid StudentDTO dto) {
        return studentRepository.findById(id).map(s -> {
            s.setName(dto.getName());
            s.setEmail(dto.getEmail());
            return studentRepository.save(s);
        }).orElseThrow(() -> new RuntimeException("Etudiant non trouve : " + id));
    }

    @GetMapping("/search")
    public List<Student> search(@RequestParam String name) {
        return studentRepository.findByName(name);
    }

    @GetMapping("/page")
    public Page<Student> getAllPaged(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @PostMapping("/{id}/courses/{courseId}")
    public Student inscrire(@PathVariable Long id, @PathVariable Long courseId) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouve : " + id));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Cours non trouve : " + courseId));
        student.getCourses().add(course);
        return studentRepository.save(student);
    }
}