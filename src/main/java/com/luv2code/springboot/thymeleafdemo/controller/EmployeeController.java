package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService theEmployeeService){
		employeeService = theEmployeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		// get the employees from db
		List<Employee> theEmployees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){

		// create model attribute to bind the form data
		Employee theEmployee = new Employee();
		theModel.addAttribute("employees",theEmployee);

		return "employees/employees-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFromForUpdate(@RequestParam("employeeId") int theId,Model theModel){
		//get the employees from service
		Employee theEmployee = employeeService.findById(theId);

		// set employees in the model to prepopulate the form
		theModel.addAttribute("employees",theEmployee);

		// send over to our form
		return "employees/employees-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){

		// Save the Employee

		employeeService.save(theEmployee);

		return "redirect:/employees/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId){

		// Save the Employee

		employeeService.deleteById(theId);

		return "redirect:/employees/list";
	}
}









