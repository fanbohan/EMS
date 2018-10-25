package com.vincent.ems.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vincent.ems.dao.EmployeeDao;
import com.vincent.ems.domain.Employee;

public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {
	// HibernateDaoSupport 有sessionFactory，需注入；

	@Override
	public Employee findByUsernameAndPassword(Employee emloyee) {
		
		String hql = "from Employee where username = ? and password = ?";

		List<Employee> list = this.getHibernateTemplate().find(hql, emloyee.getUsername(), emloyee.getPassword());
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Employee> findByPage(Integer begin, Integer size) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		List<Employee> employees = this.getHibernateTemplate().findByCriteria(criteria, begin, size);
		return employees;
	}

	@Override
	public int findCount() {
		String hql = "select count(*) from Employee";
		List<Long> employees = getHibernateTemplate().find(hql);
		if(employees.size() > 0){
			return employees.get(0).intValue();
		}
		
		return 0;
	}

	@Override
	public Employee findById(Integer eid) {
		return this.getHibernateTemplate().get(Employee.class, eid);
	}

	@Override
	public void update(Employee employee) {
		this.getHibernateTemplate().update(employee);
	}

	@Override
	public void delete(Employee employee) {
		this.getHibernateTemplate().delete(employee);
	}

	@Override
	public void save(Employee employee) {
		this.getHibernateTemplate().save(employee);
	}
}
