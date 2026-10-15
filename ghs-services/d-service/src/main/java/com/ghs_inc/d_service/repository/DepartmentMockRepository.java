package com.ghs_inc.d_service.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ghs_inc.d_service.model.Department;

@Repository
public class DepartmentMockRepository {
	
	private static final Logger LOG = LoggerFactory.getLogger(DepartmentMockRepository.class);
	
	private List<Department> departments = new ArrayList<>();

	public List<Department> findAll(){
		LOG.info("findAll is invoked {}", departments);
		return departments;
	}
	
	public Department findById(Long id) {
		LOG.info("findById is invoked {}", id);
		return departments.stream().filter(d -> d.getId().equals(id)).findFirst().orElseThrow();
	}
	
	public Department add(Department department) {
		LOG.info("add is invoked {}", department);
		boolean isAdded = departments.add(department);
		return isAdded? department: null;
	}
}
