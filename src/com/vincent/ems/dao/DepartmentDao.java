package com.vincent.ems.dao;

import java.util.List;

import com.vincent.ems.domain.Department;

public interface DepartmentDao {
	
	public int findCount();
	
	public List<Department> findByPage(Integer begin, Integer pageSize);
	
	public void save(Department department);

	public Department findById(Integer did);

	public void update(Department department);

	public void delete(Department department);

	public List<Department> findAll();
}
