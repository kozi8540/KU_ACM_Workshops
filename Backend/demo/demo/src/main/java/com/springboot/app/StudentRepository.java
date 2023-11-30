package com.springboot.app;

import com.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

// Class for managing students in student database
public interface StudentRepository extends JpaRepository<Student, Long> {
}