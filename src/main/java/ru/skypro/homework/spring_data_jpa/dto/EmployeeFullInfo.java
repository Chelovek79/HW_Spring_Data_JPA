package ru.skypro.homework.spring_data_jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class EmployeeFullInfo {

    private String name;
    private Integer salary;
    private String positionName;

    public EmployeeFullInfo(String name, String positionName, Integer salary) {
        this.name = name;
        this.positionName = positionName;
        this.salary = salary;
    }
}
