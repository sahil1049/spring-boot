package com.vaman.spring.boot.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaman.spring.boot.demo.App;
import com.vaman.spring.boot.demo.model.Department;
import com.vaman.spring.boot.demo.repository.DepartmentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;
	static Logger logger = LoggerFactory.getLogger(DepartmentService.class);
	public List<Department> getAllDepartments() {
		logger.info("getAllDepartments service");
		
		try {
		List<Department> departmentsList = new ArrayList<Department>();
		departmentRepository.findAll().forEach(dept -> departmentsList.add(dept));
		
		return departmentsList;
		}
		catch(UnsupportedOperationException u) {
			logger.error(u.getMessage());
			
		}
		return null;
		
	}

	public Department getDepartmentById(int id) {
		logger.info("getDepartmentById service");
		
		try {
		return departmentRepository.findById(id).get();
		}catch(NoSuchElementException i) {
			logger.error(i.getMessage());
		}
		return null;
		
	}

	public void createDepartment(Department department) {
		logger.info("createDepartment service");
		
		try {
		departmentRepository.save(department);
		}
		catch(IllegalArgumentException i) {
			logger.info(i.getMessage());
		}
	}

	public Optional<Department> update(Department department, int id) {
		logger.info("update service");
		try {
		return departmentRepository.findById(id).map(dept -> {
			dept.setId(dept.getId()); // check here
			dept.setName(department.getName());
			dept.setCity(department.getCity());
			return departmentRepository.save(dept);
		});
		}
		catch(IllegalArgumentException i) {
			logger.error(i.getMessage());;
		}
		return null;
	}

	public void delete(int id) {
		logger.info("delete service");
		try {
		departmentRepository.deleteById(id);
		}
		catch(IllegalArgumentException i) {
			logger.error(i.getMessage());
		}
		
	}

}
