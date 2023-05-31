package ru.skypro.homework.spring_data_jpa.service;

import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.skypro.homework.spring_data_jpa.dto.EmployeeDTO;
import ru.skypro.homework.spring_data_jpa.dto.EmployeeFullInfo;
import ru.skypro.homework.spring_data_jpa.entity.Employee;
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
        List<Employee> employees = (List<Employee>) employeeRepository.getAllEmployee();
        return employees.stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeDTOById(int id) {
        checkId(id);
        return fromEmployee(employeeRepository.getEmployeeById(id));
    }

    @Override
    public void createNewOne(EmployeeDTO employeeDTO) {
        Employee employee = employeeDTO.toEmployee();
        employeeRepository.createNewOne(employee.getName(), employee.getSalary());
    }

    @Override
    public void deletingEmployeeById(int id) {
        checkId(id);
        employeeRepository.deleteEmployeeById(id);
    }

    @Override
    public void changeEmployeeById(int id, EmployeeDTO employeeDTO) {
        checkId(id);
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

    @Override
    public List<EmployeeFullInfo> getEmployeeWithMaxSalary() {
        return employeeRepository.getEmployeeWithMaxSalary();
    }

    @Override
    public EmployeeFullInfo getEmployeeByIdFullInfo(int id) {
        checkId(id);
        return employeeRepository.getEmployeeByIdFullInfo(id);
    }

    @Override
    public List<EmployeeDTO> getEmployeeByOnePosition(String position) {
        return employeeRepository.getEmployeeByOnePosition(position).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeFullInfo> getEmployeeWithPaging(int pageIndex, int unitPerPage) {
        PageRequest employeeOfConcretePage = PageRequest.of(pageIndex, unitPerPage);
        Page<EmployeeFullInfo> page = employeeRepository.findAllpage(employeeOfConcretePage);
        return page.stream()
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public void checkId(int id) {
        if (!employeeRepository.checkId(id)) {
            throw new Exception("Табельный номер отсутствует в списке.");
        }
    }
}
