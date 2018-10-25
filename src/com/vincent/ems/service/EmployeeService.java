package com.vincent.ems.service;

import com.vincent.ems.domain.Employee;
import com.vincent.ems.domain.PageBean;

/*
 * 员工管理的业务层接口
 */
public interface EmployeeService {
	public Employee login(Employee employee);

	public PageBean<Employee> findByPage(Integer currPage);

	public Employee findById(Integer eid);

	public void update(Employee employee);

	public void delete(Employee employee);

	public void save(Employee employee);
}
