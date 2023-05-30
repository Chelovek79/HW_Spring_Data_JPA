package ru.skypro.homework.spring_data_jpa.service;

import ru.skypro.homework.spring_data_jpa.dto.EmployeeDTO;
import ru.skypro.homework.spring_data_jpa.employee.Employee;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployee();

    EmployeeDTO getEmployeeDTOById(int id);

    void createNewOne (EmployeeDTO employeeDTO);

    void deletingEmployeeById(int id);

    void changeEmployeeById(int id, EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAllEmployeeHaveSalaryHigher(int salary);
}
