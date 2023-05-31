package ru.skypro.homework.spring_data_jpa.dto;

import lombok.Data;
import ru.skypro.homework.spring_data_jpa.entity.Employee;

@Data
public class EmployeeDTO {

    private Integer id;
    private String name;
    private Integer salary;

    public EmployeeDTO() {
    }

    public static EmployeeDTO fromEmployee(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());
        return employeeDTO;
    }

    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setId(this.getId());
        employee.setName(this.getName());
        employee.setSalary(this.getSalary());
        return employee;
    }
}
