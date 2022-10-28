package am.itspace.company_employee_spring.controller;

import am.itspace.company_employee_spring.entity.Company;
import am.itspace.company_employee_spring.entity.Employee;
import am.itspace.company_employee_spring.entity.Position;
import am.itspace.company_employee_spring.repository.CompanyRepository;
import am.itspace.company_employee_spring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/employees")
    public String employees(ModelMap modelMap) {
        List<Employee> employeeList = employeeRepository.findAll();
        List<Company> companyList = companyRepository.findAll();
        modelMap.addAttribute("employees", employeeList);
        modelMap.addAttribute("companies", companyList);
        return "employee";

    }

    @GetMapping("/employee/add")
    public String addEmployeePage(ModelMap modelMap) {
        List<Company> companyList = companyRepository.findAll();
        Position[] values = Position.values();
        modelMap.addAttribute("companies", companyList);
        modelMap.addAttribute("position", values);
        return "addemployee";

    }

    @PostMapping("/employee/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        Company company = companyRepository.getById(employee.getCompany().getId());
        company.setSize(company.getSize() + 1);
        companyRepository.save(company);
        employeeRepository.save(employee);
        return "redirect:/employees";
    }
}
