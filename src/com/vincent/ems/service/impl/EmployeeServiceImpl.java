package com.vincent.ems.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.vincent.ems.dao.EmployeeDao;
import com.vincent.ems.domain.Employee;
import com.vincent.ems.domain.PageBean;
import com.vincent.ems.service.EmployeeService;

@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public Employee login(Employee employee) {
		Employee existEmloyee = employeeDao.findByUsernameAndPassword(employee);
		
		return existEmloyee;
	}

	@Override
	public PageBean<Employee> findByPage(Integer currPage) {
		PageBean<Employee> pageBean = new PageBean<Employee>();
		
		// 封装pageBean
		pageBean.setCurrPage(currPage);
		int pageSize = 3;
		pageBean.setPageSize(pageSize);

		int totalCount = employeeDao.findCount();
		pageBean.setTotalCount(totalCount);

		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());

		int begin = (currPage - 1) * pageSize;

		
		pageBean.setList(this.employeeDao.findByPage(begin,pageSize)) ;
		
		return pageBean;
	}

	@Override
	public Employee findById(Integer eid) {
		Employee employee = this.employeeDao.findById(eid);
		return employee;
	}

	@Override
	public void update(Employee employee) {
		this.employeeDao.update(employee);
		
	}

	@Override
	public void delete(Employee employee) {
		this.employeeDao.delete(employee);
	}

	@Override
	public void save(Employee employee) {
		this.employeeDao.save(employee);
	}
}
