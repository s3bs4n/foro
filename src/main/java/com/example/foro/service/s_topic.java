package com.example.foro.service;

import com.example.foro.model.c_topic;
import java.util.List;
import java.util.Optional;


public class s_topic {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long id);
    Student createStudent(Student student);
    Student updateStudent(Long id,Student student);
    void deleteStudent(Long id);
}
