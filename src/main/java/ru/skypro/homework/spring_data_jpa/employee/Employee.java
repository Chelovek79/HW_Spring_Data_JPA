package ru.skypro.homework.spring_data_jpa.employee;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employeejpa")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int salary;

    @JsonCreator
    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return id + ". " + name + " Зарплата = " + salary + " руб.";
    }
}
