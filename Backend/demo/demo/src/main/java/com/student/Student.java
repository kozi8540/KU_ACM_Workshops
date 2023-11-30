package com.student;

import jakarta.persistence.*;
import lombok.Data;     // Used to automatically generate getters/setters

@Data       // Automatically generates getters/setters with lombok library
@Entity     // Marks the class as something that can be used in a database
@Table(name="students")    // Specifies table name for entity

// Student class to be added to student database
public class Student {
    @Id     // Declares that the variable will be used as the entity's id
	@GeneratedValue(strategy = GenerationType.IDENTITY)     // Automatically generates unique id
    private long id;

    @Column(name = "first_name", nullable = false)      // Declares the column the variable belongs to
	private String firstName;
	
	@Column(name = "last_name")     // Declares the column the variable belongs to
	private String lastName;
}