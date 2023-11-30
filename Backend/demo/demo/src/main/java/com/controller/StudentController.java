package com.controller;

import java.util.List;

import com.service.StudentService;
import com.student.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     // Indicates that returned data will be response bodies
@RequestMapping("/students")    // Sets the url for the class
public class StudentController {
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	// REST API to add student
	// http://localhost:8080/students
	@PostMapping()  // Identify as post command
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {  // Expects student as request body
		return new ResponseEntity<Student>(studentService.saveStudent(student), HttpStatus.CREATED);
	}
	
	// REST API to get all students
	// http://localhost:8080/students
	@GetMapping     // Identify as get command
	public List<Student> getAllStudents(){
		return studentService.getAllStudents();
	}
		
	// REST API for updating student
	// http://localhost:8080/students/1
	@PutMapping("{id}")     // Identify as put command
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {   // Expects student as request body
		return new ResponseEntity<Student>(studentService.updateStudent(student, id), HttpStatus.OK);
	}
	
	// REST API to delete a student
	// http://localhost:8080/students/1
	@DeleteMapping("{id}")      // Identfiy as delete command
	public ResponseEntity<String> deleteStudent(@PathVariable("id") long id){
		// Delete student from database
		studentService.deleteStudent(id);
		return new ResponseEntity<String>("Student deleted successfully!.", HttpStatus.OK);
	}
}