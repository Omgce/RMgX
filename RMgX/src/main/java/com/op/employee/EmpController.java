package com.op.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmpController {

	@Autowired
	public EmpRepository repo;
	
	@GetMapping("/employee")
	public String listEmployee(Model model) {
		List<Employee> listEmployee=repo.findAll();
		model.addAttribute("listEmployee", listEmployee);
		return "employee";
	}
	
	@GetMapping("/employee/new")
	public String showEmployeeNewForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "emp_form";
	}
	
	@PostMapping("/employee/save")
	public String saveEmployee(Employee employee) {
		repo.save(employee);
		return "redirect:/employee";
	}
}
