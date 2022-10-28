package am.itspace.company_employee_spring.controller;


import am.itspace.company_employee_spring.entity.Address;
import am.itspace.company_employee_spring.entity.Company;
import am.itspace.company_employee_spring.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/company")
    public String company(ModelMap modelMap){
        List<Company> companyList = companyRepository.findAll();
        modelMap.addAttribute("company", companyList);
        return "company";
    }

    @GetMapping("/company/add")
    public String addCompanyPage(ModelMap modelMap){
        Address[] addresses = Address.values();
        modelMap.addAttribute("companyaddress",addresses);
        return "addCompany";

    }
    @PostMapping("/company/add")
    public String addcompany(@ModelAttribute Company company){
        companyRepository.save(company);
        return "redirect:/company";
    }

    @GetMapping("/company/delete/{id}")
    public String deleteCompany(@PathVariable("id") int id){
        companyRepository.deleteById(id);
        return "redirect:/company";
    }

}
