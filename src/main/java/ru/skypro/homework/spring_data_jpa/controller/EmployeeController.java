package ru.skypro.homework.spring_data_jpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.spring_data_jpa.dto.EmployeeDTO;
import ru.skypro.homework.spring_data_jpa.employee.Employee;
import ru.skypro.homework.spring_data_jpa.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/all")
    public List<EmployeeDTO> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable String id) {
        int idEmployee = Integer.parseInt(id);
        return employeeService.getEmployeeDTOById(idEmployee);
    }

    @PostMapping("/addOne/")
    public void createNewOne(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.createNewOne(employeeDTO);
    }

    @DeleteMapping("/{id}")
    public void deletingEmployeeById(@PathVariable String id) {
        int idEmployee = Integer.parseInt(id);
        employeeService.deletingEmployeeById(idEmployee);
    }

    @PutMapping("/{id}")
    public void changeEmployeeById(@PathVariable String id, @RequestBody EmployeeDTO employeeDTO) {
        int idEmployee = Integer.parseInt(id);
        employeeService.changeEmployeeById(idEmployee, employeeDTO);
    }

    @GetMapping("/salaryHigherThan")
    public List<EmployeeDTO> getAllEmployeeHaveSalaryHigher(@RequestParam("salary") Integer salary) {
        return employeeService.getAllEmployeeHaveSalaryHigher(salary);
    }
}
