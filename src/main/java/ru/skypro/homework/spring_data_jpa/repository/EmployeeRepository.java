package ru.skypro.homework.spring_data_jpa.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.spring_data_jpa.employee.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE employeejpa SET " +
            "name =:name," +
            "salary =:salary " +
            "WHERE id =:id",
            nativeQuery = true)
    void changeEmployeeById(@Param("id") int id, @Param("name") String name, @Param("salary") int salary);

    List<Employee> findBySalaryGreaterThan(int salary);
}
