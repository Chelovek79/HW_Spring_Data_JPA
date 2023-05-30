package ru.skypro.homework.spring_data_jpa.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.skypro.homework.spring_data_jpa.dto.EmployeeDTO;
import ru.skypro.homework.spring_data_jpa.employee.Employee;
import ru.skypro.homework.spring_data_jpa.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.skypro.homework.spring_data_jpa.dto.EmployeeDTO.fromEmployee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public EmployeeDTO getEmployeeDTOById(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new Exception("Работник с таким табельным номером отсутствует."));
        return fromEmployee(employee);
    }

    @Override
    public void createNewOne(EmployeeDTO employeeDTO) {
        employeeRepository.save(employeeDTO.toEmployee());
    }

    @Override
    public void deletingEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void changeEmployeeById(int id, EmployeeDTO employeeDTO) {
        Employee employee = employeeDTO.toEmployee();
        String name = employee.getName();
        int salary = employee.getSalary();
        employeeRepository.changeEmployeeById(id, name, salary);
    }

    @Override
    public List<EmployeeDTO> getAllEmployeeHaveSalaryHigher(int salary) {
        return employeeRepository.findBySalaryGreaterThan(salary).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }
}
