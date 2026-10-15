package com.ghs_inc.d_service.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department {
	
	public Department() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(employees, id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(employees, other.employees) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	private Long id;
	private String name;
	private List<Employee> employees = new ArrayList<>();

}
