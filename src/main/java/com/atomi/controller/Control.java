package com.atomi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atomi.entity.Employee;
import com.atomi.service.EmployeeService;

import jakarta.validation.Valid;

@Controller
public class Control {
	private final EmployeeService employeeService;

	public Control(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/")
	public String main() {
		return "Main.html";
	}

	@GetMapping("/form")
	public String form(ModelMap m) {
		if (!m.containsAttribute("employee")) {
			m.put("employee", new Employee());
		}
		return "Add.html";
	}

	@GetMapping("/view")
	public String view(ModelMap m) {
		m.put("ans", employeeService.getAllEmployees());
		return "View.html";
	}

	@PostMapping("/add")
	public String add(@Valid @ModelAttribute("employee") Employee e, BindingResult result, RedirectAttributes r) {
		if (result.hasErrors()) {
			return "Add.html";
		}
		employeeService.addEmployee(e);
		r.addFlashAttribute("Saved", "Emlployee Details Added");
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String del(@PathVariable Integer id, RedirectAttributes r) {
		employeeService.deleteEmployee(id);
		r.addFlashAttribute("msg", "Data deleted");
		return "redirect:/view";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, ModelMap m) {
		m.put("employee", employeeService.getEmployeeById(id));
		return "Edit.html";
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("employee") Employee e, BindingResult result, RedirectAttributes r) {
		if (result.hasErrors()) {
			return "Edit.html";
		}
		employeeService.updateEmployee(e);
		r.addFlashAttribute("m", "Updated Successfully");
		return "redirect:/view";

	}

}
