package com.vincent.ems.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.vincent.ems.domain.Department;
import com.vincent.ems.domain.PageBean;
import com.vincent.ems.service.DepartmentService;

public class DepartmentAction extends ActionSupport implements ModelDriven<Department> {

	private static final long serialVersionUID = 4032008961951903039L;

	Department department = new Department();
	
	//用于向前台返回数据
	@Override
	public Department getModel() {
		return department;
	}

	private Integer currPage = 1;
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	
	
	private DepartmentService departmentService;
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	public String findAll(){
		PageBean<Department> pageBean = departmentService.findByPage(currPage);
		
		//将pageBean存入到值栈中；
		ActionContext.getContext().getValueStack().push(pageBean);;
		return "findAll";
	}
	

	public String save(){
		this.departmentService.save(department);
		return "saveSuccess";
	}
	
	public String saveUI(){
		return "saveUI";
	}
	
	public String edit(){
		department = departmentService.findById(department.getDid());
		return "editSuccess";
	}
	
	public String update(){
		this.departmentService.update(department);
		return "updateSuccess";
	}
	
	public String delete(){
		//先找到该项，再进行删除，可实现级联删除
		department = this.departmentService.findById(department.getDid());
		this.departmentService.delete(department);
		return "deleteSuccess";
	}
	
}
