package com.example.hoy.controller;

import com.example.hoy.Service.CompanyService;
import com.example.hoy.Service.TransactionService;
import com.example.hoy.model.dto.CompanyDto;
import com.example.hoy.model.dto.TransactionDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class MainController {

    private final CompanyService companyService;
    private final TransactionService transactionService;


    @GetMapping("/Home")
    public String index() {

        return "index";  // View name for the registration form
    }
    @GetMapping("/Dashboard")
    public String Dashboard() {

        return "Dashboard";  // View name for the registration form
    }
    @GetMapping("/team")
    public String team() {

        return "team";  // View name for the registration form
    }
    @GetMapping("/service")
    public String service() {

        return "service";  // View name for the registration form
    }
    @GetMapping("/why")
    public String why() {

        return "why";  // View name for the registration form
    }
    @GetMapping("/about")
    public String about() {

        return "about";  // View name for the registration form
    }



    @GetMapping("/Transaction")
    public String   transaction(Model model) {
        model.addAttribute("company", new TransactionDto());
        return "Transaction";  // View name for the registration form
    }



    @GetMapping("/Transaction2")
    public String   transaction2(Model model) {
        model.addAttribute("company", new TransactionDto());
        return "Transaction2";  // View name for the registration form
    }

    @PostMapping("/Transaction")
    public String transaction(@ModelAttribute("company") TransactionDto transactionDto, Model model) {
        boolean isValid = companyService.checkCompany(transactionDto);

        if (isValid) {
            model.addAttribute("message", "Transaction successful!");
            return "TransactionSuccess"; // Name of the view for a successful transaction
        } else {
            model.addAttribute("error", "Invalid bank code or other issues.");
            return "Error"; // Name of the view for error handling
        }
    }
//    @PostMapping("/Transaction")
//    public String registerTransaction(@ModelAttribute("transaction") TransactionDto transactionDto, Model model) {
//          // Assuming this method saves the company data
//        TransactionDto transactionDto = TransactionService.save(transactionDto);
//        model.addAttribute("message", "Company registered successfully!");
//        return "Registration";  // View name for success message or redirect
//    }


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("companyDto", new CompanyDto());
        return "registration";
    }

    // POST request to handle form submission
    @PostMapping("/registration")
    public String registerCompany(@ModelAttribute("companyDto") CompanyDto companyDto, Model model) {
        companyService.saveCompany(companyDto);  // Assuming this method saves the company data
        //System.out.println(companyDto.toString());
        model.addAttribute("message", "Company registered successfully!");
        return "redirect:registration";  // View name for success message or redirect
    }
}
//
//@Controller
//@RequestMapping("/")
//@AllArgsConstructor
//public class MainController {
//
//    private final CompanyService companyService;
//
//
//    @GetMapping
//    public String getEmployeePage(Model model) {
//        model.addAttribute("ServerTime", new Date());
//        //model.addAttribute("employee", new Employee(5L,"Hanaa"));
//        return "index";
//    }
//
//    @PostMapping("/registration")
//    public String addCompany (@ModelAttribute("reg") CompanyDto company, Model model) {
//        CompanyEntity com = companyService.save(company);
//        model.addAttribute("reg", com);
//        return "Registration";
//    }
//    public String addEmployee (@ModelAttribute("employee") Employee emp, Model model){
//        Employee em = employeeService.save(emp);
//        model.addAttribute("employee", em);
//        return "Registration" ;
//    }
//    @PostMapping("/registration")
//    public ResponseEntity<?> registerUser() {
//
//        return new ResponseEntity("", HttpStatus.OK);
//    }

//}
