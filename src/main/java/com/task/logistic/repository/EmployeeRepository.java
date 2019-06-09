package com.task.logistic.repository;

import com.task.logistic.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByNameAndLastName(String name, String lastName);

    Employee findByPassport(String passport);

    @Query(value = "SELECT * FROM employee WHERE crew_id = :id", nativeQuery = true)
    List<Employee> getEmployeeOfCrew(@Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE employee SET crew_id = :id WHERE name = :name and last_name = :lastname", nativeQuery = true)
    void update(@Param("id") Long id, @Param("name") String name, @Param("lastname") String lastname);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE employee SET crew_id = null WHERE name = :name and last_name = :lastname", nativeQuery = true)
    void deletingCrew(@Param("name") String name, @Param("lastname") String lastname);
}
