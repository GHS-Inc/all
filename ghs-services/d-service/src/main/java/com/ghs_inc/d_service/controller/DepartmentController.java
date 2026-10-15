package com.ghs_inc.d_service.controller;

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

import com.ghs_inc.d_service.model.Department;
import com.ghs_inc.d_service.repository.DepartmentMockRepository;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	private final static Logger LOG = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentMockRepository mockRepository;
	
	@PostMapping
	public Department add(@RequestBody Department department) {
		LOG.info("add is invoked {}", department);
		return mockRepository.add(department);
	}
	
	@GetMapping("/{id}")
	public Department findById(@PathVariable Long id) {
		LOG.info("findById is invoked {}", id);
		return mockRepository.findById(id);
	}
	
	@GetMapping
	public List<Department> findAll(){
		LOG.info("findAll is invoked");
		return mockRepository.findAll();
	}

}
