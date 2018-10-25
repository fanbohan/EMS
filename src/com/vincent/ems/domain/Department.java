package com.vincent.ems.domain;

import java.util.HashSet;
import java.util.Set;

public class Department {
	private Integer did;
	private String dname;
	private String ddesc;
	
	private Set<Employee> emloyees = new HashSet<Employee>();
	
	public Set<Employee> getEmloyees() {
		return emloyees;
	}
	public void setEmloyees(Set<Employee> emloyees) {
		this.emloyees = emloyees;
	}
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDdesc() {
		return ddesc;
	}
	public void setDdesc(String ddesc) {
		this.ddesc = ddesc;
	}
}
