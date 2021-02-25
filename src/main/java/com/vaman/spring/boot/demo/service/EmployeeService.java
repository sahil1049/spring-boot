package com.vaman.spring.boot.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.hibernate.TypeMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaman.spring.boot.demo.model.Employee;
import com.vaman.spring.boot.demo.repository.DepartmentRepository;
import com.vaman.spring.boot.demo.repository.EmployeeRepository;

//defining the business logic

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	DepartmentRepository departmentRepository;
	static Logger logger = LoggerFactory.getLogger(DepartmentService.class);
//getting all Employee record by using the method findaAll() of CrudRepository

	public List<Employee> getAllEmployees() {
		logger.info("getAllEmployees service");
		try {
			List<Employee> employeesList = new ArrayList<Employee>();
			employeeRepository.findAll().forEach(emp -> employeesList.add(emp));
			return employeesList;
		} catch (UnsupportedOperationException u) {
			logger.error(u.getMessage());
		}
		catch(NullPointerException n) {
			logger.error(n.getMessage());
		}
		return null;
	}

//getting a specific record by using the method findById() of CrudRepository

	public Employee getEmployeeById(int id) {
		logger.info("getEmployeeById service");
		try {
		return employeeRepository.findById(id).get();
		}
		catch(NoSuchElementException n) {
			logger.error(n.getMessage());
		}
		return null;
	}

// create a new employee record by using the custom method in the Repository

	public Employee saveEmployee(int departmentId, Employee employee) {
		logger.info("saveEmployee service");
		
		departmentRepository.findById(departmentId).map(department -> {
			employee.setDepartment(department);
			return employee;
		});
		return employeeRepository.save(employee);
		
	
		
	}

// updating a record

	public Optional<Employee> update(Employee employee, int id) {
		logger.info("update service");
		try {
		return employeeRepository.findById(id).map(emp -> { // add more logic
			emp.setName(employee.getName());
			return employeeRepository.save(emp);
		});
		
		}
		catch(IllegalArgumentException i) {
			logger.error(i.getMessage());
		}
		return null;
	}

// deleting the specific record by using the method deleteById() of CrudRepository

	public int delete(int id) {
		logger.info("delete service");
		try {
		employeeRepository.deleteById(id);
		return id;
		}
		catch(IllegalArgumentException i) {
			logger.error(i.getMessage());
			
		}
		return 0;
	}

}
