package com.login.xminds.service;

import com.login.xminds.dto.EmployeeListResponse;
import com.login.xminds.dto.EmployeeRequestDTO;
import com.login.xminds.dto.EmployeeResponseDTO;
import com.login.xminds.dto.StatusDTO;
import org.springframework.data.domain.Sort;

/**
 * The EmployeeService.
 */
public interface EmployeeService {

    /**
     *
     * @param request request
     * @return employeeAdd
     * @throws Exception Exception
     */
    EmployeeResponseDTO  employeeAdd(EmployeeRequestDTO request) throws Exception;

    /**
     *
     * @param employeeId employeeId
     * @return deleteEmployee
     * @throws Exception Exception
     */
    StatusDTO deleteEmployee(Long employeeId) throws Exception;

    /**
     *
     * @param page page
     * @param size size
     * @param sort sort
     * @param employeeId employeeId
     * @return getPaginatedEmployeeList
     */
    EmployeeListResponse getPaginatedEmployeeList(int page, int size, Sort.Direction sort, Long employeeId);

}
