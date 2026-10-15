package com.ghs_inc.e_service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ghs_inc.e_service.model.Employee;
import com.ghs_inc.e_service.repository.EmployeeMockRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
private final static Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeMockRepository mockRepository;
	
	@PostMapping
	public Employee add(@RequestBody Employee department) {
		LOG.info("add is invoked {}", department);
		return mockRepository.add(department);
	}
	
	@GetMapping("/{id}")
	public Employee findById(@PathVariable Long id) {
		LOG.info("findById is invoked {}", id);
		return mockRepository.findById(id);
	}
	
	@GetMapping
	public List<Employee> findAll(){
		LOG.info("findAll is invoked");
		return mockRepository.findAll();
	}

	@GetMapping("/department/{id}")
	public List<Employee> findByDepartment(@PathVariable("id") Long departmentId){
		LOG.info("findByDepartment is invoked {}", departmentId);
		return mockRepository.findByDepartmentId(departmentId);
	}
}
