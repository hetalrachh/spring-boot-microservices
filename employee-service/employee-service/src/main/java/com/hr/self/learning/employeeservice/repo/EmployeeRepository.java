package com.hr.self.learning.employeeservice.repo;

import com.hr.self.learning.employeeservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public Employee findEmployeeById(int id);
}
