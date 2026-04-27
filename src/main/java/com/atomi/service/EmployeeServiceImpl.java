package com.atomi.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.atomi.entity.Employee;
import com.atomi.exception.EmployeeNotFoundException;
import com.atomi.exception.NoEmployeesFoundException;
import com.atomi.exception.ServiceException;
import com.atomi.repository.Employeerepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final Employeerepo employeeRepo;

	public EmployeeServiceImpl(Employeerepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeRepo.findAll();
		if (employees.isEmpty()) {
			throw new NoEmployeesFoundException("No employee data found");
		}
		return employees;
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		return employeeRepo.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
	}

	@Override
	public Employee addEmployee(Employee employee) {
		return saveEmployee(employee, "Unable to save employee ");
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		if (employee.getId() == null || !employeeRepo.existsById(employee.getId())) {
			throw new EmployeeNotFoundException("Employee not found with id: " + employee.getId());
		}
		return saveEmployee(employee, "Unable to update employee ");
	}

	@Override
	public void deleteEmployee(Integer id) {
		if (!employeeRepo.existsById(id)) {
			throw new EmployeeNotFoundException("Employee not found with id: " + id);
		}
		try {
			employeeRepo.deleteById(id);
		} catch (DataAccessException ex) {
			throw new ServiceException("Unable to delete employee ", ex);
		}
	}

	private Employee saveEmployee(Employee employee, String errorMessage) {
		try {
			return employeeRepo.save(employee);
		} catch (DataAccessException ex) {
			throw new ServiceException(errorMessage, ex);
		}
	}
}
