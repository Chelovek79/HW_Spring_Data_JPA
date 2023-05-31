package ru.skypro.homework.spring_data_jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "post")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Position {

    @Id
    @Column(name = "shared_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sharedId;
    @Column(name = "position")
    private String position;

    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Employee> employeeList;

}
