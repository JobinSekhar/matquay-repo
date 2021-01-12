package com.login.xminds.repository;

import com.login.xminds.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * The UserRepository.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    /**
     *
     * @param employeeId employeeId
     * @param pageable pageable
     * @return findByEmployeeId
     */
    Page<Employee> findByEmployeeId(Long employeeId, Pageable pageable);
}
