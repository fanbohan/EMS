package com.vincent.ems.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vincent.ems.dao.DepartmentDao;
import com.vincent.ems.domain.Department;

public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao {

	@Override
	public List<Department> findByPage(Integer begin, Integer pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
		List<Department> departments = this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return departments;
	}

	@Override
	public int findCount() {
		String hql = "select count(*) from Department";
		List<Long> result = this.getHibernateTemplate().find(hql);
		if (result.size() > 0) {
			return result.get(0).intValue();
		}

		return 0;
	}

	@Override
	public void save(Department department) {
		this.getHibernateTemplate().save(department);
		
	}

	@Override
	public Department findById(Integer did) {
		return this.getHibernateTemplate().get(Department.class, did);
	}

	@Override
	public void update(Department department) {
		this.getHibernateTemplate().update(department);
	}

	@Override
	public void delete(Department department) {
		this.getHibernateTemplate().delete(department);
	}

	@Override
	public List<Department> findAll() {
		return this.getHibernateTemplate().find("from Department");
	}

}
