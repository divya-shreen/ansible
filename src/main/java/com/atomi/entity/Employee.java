package com.atomi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Name is required")
	@Pattern(regexp = "^[A-Za-z ]{2,50}$", message = "Name must contain only letters and spaces")
	private String name;

	@NotNull(message = "Mobile number is required")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Mobile number must be a valid 10-digit number")
	private String mobile;

	@NotNull(message = "Salary is required")
	@Min(value = 1, message = "Salary must be greater than 0")
	private Integer salary;

	@NotBlank(message = "Department is required")
	private String department;

	@NotBlank(message = "Date of joining is required")
	private String doj;
}
