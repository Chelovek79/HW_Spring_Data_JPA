package ru.skypro.homework.spring_data_jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.spring_data_jpa.dto.EmployeeFullInfo;
import ru.skypro.homework.spring_data_jpa.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

    @Query(value = "SELECT * FROM employeejpa", nativeQuery = true)
    List<Employee> getAllEmployee();

    Employee getEmployeeById(int id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO employeejpa (name, salary)" +
            " VALUES (?1, ?2)", nativeQuery = true)
    void createNewOne(String name, int salary);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM employeejpa " +
            "WHERE id =:id", nativeQuery = true)
    void deleteEmployeeById(@Param("id") int id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE employeejpa SET " +
            "name =:name," +
            "salary =:salary " +
            "WHERE id =:id",
            nativeQuery = true)
    void changeEmployeeById(@Param("id") int id, @Param("name") String name, @Param("salary") int salary);

    List<Employee> findBySalaryGreaterThan(int salary);

    @Query("SELECT new ru.skypro.homework.spring_data_jpa.dto." +
            "EmployeeFullInfo(e.name , e.salary , p.position) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p and e.salary = (select MAX (e.salary) from Employee e)")
    List<EmployeeFullInfo> getEmployeeWithMaxSalary();

    @Query("SELECT new ru.skypro.homework.spring_data_jpa.dto." +
            "EmployeeFullInfo(e.name , e.salary , p.position) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p and e.id =:id")
    EmployeeFullInfo getEmployeeByIdFullInfo(@Param("id") int id);

    @Query(value = "SELECT id, name, salary, employeejpa.shared_id " +
            "FROM employeejpa " +
            "LEFT JOIN post ON employeejpa.shared_id = post.shared_id " +
            "WHERE post.position =:position", nativeQuery = true)
    List<Employee> getEmployeeByOnePosition(@Param("position") String position);

    @Query("SELECT new ru.skypro.homework.spring_data_jpa.dto." +
            "EmployeeFullInfo(e.name , e.salary , p.position) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p")
    Page<EmployeeFullInfo> findAllpage (PageRequest employeeOfConcretePage);

    @Query(value = "SELECT EXISTS(SELECT * FROM employeejpa " +
            "WHERE id =:id)", nativeQuery = true)
    boolean checkId (@Param("id") int id);
}
