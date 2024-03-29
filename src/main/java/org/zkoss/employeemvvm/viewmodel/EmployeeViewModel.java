package org.zkoss.employeemvvm.viewmodel;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.employeemvvm.bean.Employee;
import org.zkoss.employeemvvm.service.EmployeeService;
import org.zkoss.employeemvvm.utils.Messages;
import org.zkoss.util.logging.Log;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

public class EmployeeViewModel {

	private static final Log log = Log.lookup(EmployeeViewModel.class);
	
	private Employee _selected, _newEmployee = new Employee();
	private ListModelList<Employee> _model = EmployeeService.INSTANCE.getModel();
	
	private String _error = null;
	
	public String getError() {
		return _error;
	}
	
	@NotifyChange("error")
	public void setError(String error) {
		if(error != null)
			log.error(error);
		
		_error = error;
	}
	
	public Employee getNewEmployee() {
		return _newEmployee;
	}
	
	public Employee getSelected() {
		return _selected;
	}	
	
	@NotifyChange("selected")
	public void setSelected(Employee selected) {		
		_selected = selected;
	}

	public ListModel<Employee> getEmployees() {
		return _model;
	}
	
	@Command
	@NotifyChange({"employees", "selected"})
	public void add() {
		Employee myNewEmployee = new Employee(_newEmployee.getFirstName(),
											  _newEmployee.getLastName(),
											  _newEmployee.getAge());
		_model.add(myNewEmployee);
		setSelected(myNewEmployee);
	}
	
	@Command
	@NotifyChange({"employees", "error", "selected"})
	public void update() {
		boolean success = false;
		
		if (_selected != null) {
			int index = _model.indexOf(_selected);
			
			if(index >= 0) {
				Employee employee = _model.get(index);
				employee.setAge(_selected.getAge());
				employee.setFirstName(_selected.getFirstName());
				employee.setLastName(_selected.getLastName());
				success = true;
				setError(null);
			}
		}
	
		if(!success) {
			setError(Messages.getString("EmployeeController.2"));
		}
	
	}
	
	@Command
	@NotifyChange({"employees", "error", "selected"})
	public void remove() {

		boolean success = false;
		
		if (_selected != null) {
			int index = _model.indexOf(_selected);
			if(index >= 0) {
				_model.remove(index);
				setSelected(_selected);
				success = true;
				setError(null);
			}
		}
	
		if(!success) {
			setError(Messages.getString("EmployeeController.4"));
		}

	}
}
