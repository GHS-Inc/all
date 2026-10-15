package com.ghs_inc.e_service.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.ghs_inc.e_service.model.Employee;

@Repository
public class EmployeeMockRepository {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeMockRepository.class);
	
	private List<Employee> employees = new ArrayList<>();
	
	public List<Employee> findAll(){
		LOG.info("findAll is invoked {}", employees);
		return employees;
	}
	
	public Employee findById(Long id) {
		LOG.info("findById is invoked {}", id);
		return employees.stream().filter(e -> e.id().equals(id)).findFirst().orElseThrow();
	}
	
	public Employee add(Employee department) {
		LOG.info("add is invoked {}", department);
		boolean isAdded = employees.add(department);
		return isAdded? department: null;
	}
	
	public List<Employee> findByDepartmentId(@PathVariable("id") Long departmentId){
		return employees.stream().filter(e -> e.departmentId().equals(departmentId)).toList();
	}
}
