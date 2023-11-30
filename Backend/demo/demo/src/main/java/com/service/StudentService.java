package com.service;

import com.springboot.app.ResourceNotFoundException;
import com.springboot.app.StudentRepository;
import com.student.Student;
import org.springframework.stereotype.Service;

import java.util.List;

// Service for student that controls the student database
@Service
public class StudentService {
	private StudentRepository studentRepository;
	
    // Constructor
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

    // Adds students to student database
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

    // Gets all of the students in the student database
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

    // Updates existing students in database with new attributes
	public Student updateStudent(Student student, long id) {
		// Check if student with given id exists in database
		Student existingStudent = studentRepository.findById(id).orElseThrow(
			() -> new ResourceNotFoundException("Student", "Id", id));
        // Update information
        existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		// Save existing employee to database
		studentRepository.save(existingStudent);
		return existingStudent;
	}

    // Deletes student from student database
	public void deleteStudent(long id) {
		// Check if student with given id exists in database
		studentRepository.findById(id).orElseThrow(() -> 
			new ResourceNotFoundException("Student", "Id", id));
        // Delete student in database
        studentRepository.deleteById(id);
	}
	
}