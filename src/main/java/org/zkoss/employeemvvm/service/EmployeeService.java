package org.zkoss.employeemvvm.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.zkoss.employeemvvm.bean.Employee;
import org.zkoss.zul.ListModelList;

public enum EmployeeService {
	INSTANCE;
	
	private List<Employee> _employees = Collections.synchronizedList(new ArrayList<Employee>());
	
	private EmployeeService() {
		_employees.add(new Employee("Roger", "Charles", 21));
		_employees.add(new Employee("Gary", "Peters", 27));
		_employees.add(new Employee("Edward", "Daniels", 35));
	}
	
	public ListModelList<Employee> getModel() {
		return new ListModelList<Employee>(_employees, true);
	}
}
