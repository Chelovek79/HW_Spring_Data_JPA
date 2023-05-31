package ru.skypro.homework.spring_data_jpa.entity;

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

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "shared_id")
    private Position position;

    @JsonCreator
    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
}
