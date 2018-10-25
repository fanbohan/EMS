package com.vincent.ems.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.vincent.ems.domain.Department;
import com.vincent.ems.domain.Employee;
import com.vincent.ems.domain.PageBean;
import com.vincent.ems.service.DepartmentService;
import com.vincent.ems.service.EmployeeService;

public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>{
	
	private static final long serialVersionUID = -3439769846148102460L;
	//模型驱动需要使用的对象，将前台页面参数封装到该对象中；
	private Employee employee = new Employee();
	@Override
	public Employee getModel() {
		return employee;
	}

	
	//注入业务层的类
	private EmployeeService employeeService;
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	private DepartmentService departmentService;

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/*
	 * 登陆执行方法
	 */
	public String login() {
		//System.out.println("login!");
		Employee existEmployee = employeeService.login(employee);
		if(null == existEmployee){
			this.addActionError("用户名或密码错误！");
			return INPUT;
		}else {
			ActionContext.getContext().getSession().put("existEmployee", existEmployee);
			return SUCCESS;
		}
	}
	
	private Integer currPage = 1;
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public String findAll(){
		PageBean<Employee> pageBean = employeeService.findByPage(currPage);
		
		//将pageBean存入到值栈中；
		ActionContext.getContext().getValueStack().push(pageBean);;
		return "findAll";
	}
	
	public String save(){
		this.employeeService.save(employee);
		return "saveSuccess";
	}
	
	public String saveUI(){
		List<Department> departments = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("departments",departments);
		return "saveUI";
	}
	
	public String edit(){
		//System.out.println(employee.getEid());
		employee = employeeService.findById(employee.getEid());List<Department> departments = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("departments",departments);
		
		return "editSuccess";
	}
	
	public String update(){
		this.employeeService.update(employee);
		return "updateSuccess";
	}
	
	public String delete(){
		employee = this.employeeService.findById(employee.getEid());
		this.employeeService.delete(employee);
		return "deleteSuccess";
	}
}
