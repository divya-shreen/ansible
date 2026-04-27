package com.atomi.service;

import java.util.List;

import com.atomi.entity.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();

	Employee getEmployeeById(Integer id);

	Employee addEmployee(Employee employee);

	Employee updateEmployee(Employee employee);

	void deleteEmployee(Integer id);
}
