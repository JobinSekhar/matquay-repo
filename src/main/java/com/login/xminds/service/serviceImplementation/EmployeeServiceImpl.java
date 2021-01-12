package com.login.xminds.service.serviceImplementation;

import com.login.xminds.dto.EmployeeListResponse;
import com.login.xminds.dto.EmployeeRequestDTO;
import com.login.xminds.dto.EmployeeResponseDTO;
import com.login.xminds.dto.StatusDTO;
import com.login.xminds.entity.Employee;
import com.login.xminds.repository.EmployeeRepository;
import com.login.xminds.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.login.xminds.utils.AppConstants.DEPARTMENT;

/**
 * The EmployeeServiceImpl.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * The constructor Injection for EmployeeServiceImpl.
     *
     * @param employeeRepositoryParam employeeRepositoryParam
     */
    @Autowired
    public EmployeeServiceImpl(final EmployeeRepository employeeRepositoryParam) {
        this.employeeRepository = employeeRepositoryParam;
    }


    public EmployeeResponseDTO employeeAdd(final EmployeeRequestDTO request) throws Exception {

        if (request != null) {
            if (!DEPARTMENT.contains(request.getDepartment())) {
                throw new IOException("Department should be in the following. " + Arrays.toString(DEPARTMENT.toArray()));
            }

            if (request.getEmployeeId() != null) {
                Optional<Employee> employeeInDB = employeeRepository.findById(request.getEmployeeId());
                if (employeeInDB.isPresent()) {
//                    update employee
                    employeeInDB.get().setFirstName(request.getFirstName());
                    employeeInDB.get().setLastName(request.getLastName());
                    employeeInDB.get().setAddress(request.getAddress());
                    employeeInDB.get().setCountry(request.getCountry());
                    employeeInDB.get().setState(request.getState());
                    employeeInDB.get().setZipCode(request.getZipCode());
                    employeeInDB.get().setDepartment(request.getDepartment());
                    employeeInDB.get().setCreatedOn(LocalDateTime.now());
                    employeeRepository.save(employeeInDB.get());
                    return this.processDto(employeeInDB.get());
                } else {
                    //add new Employee
                    Employee employee = new Employee();
                    employee.setFirstName(request.getFirstName());
                    employee.setLastName(request.getLastName());
                    employee.setAddress(request.getAddress());
                    employee.setCountry(request.getCountry());
                    employee.setState(request.getState());
                    employee.setZipCode(request.getZipCode());
                    employee.setDepartment(request.getDepartment());
                    employee.setCreatedOn(LocalDateTime.now());
                    employeeRepository.save(employee);
                    return this.processDto(employee);
                }
            }
        }
        return null;
    }

    public StatusDTO deleteEmployee(final Long employeeId) throws Exception {
        StatusDTO statusDTO = new StatusDTO();
        if (employeeId != null) {
            Optional<Employee> employeeInDB = employeeRepository.findById(employeeId);
            if (employeeInDB.isPresent()) {
                employeeRepository.delete(employeeInDB.get());
                statusDTO.setStatus("SUCCESS");
                return statusDTO;
            } else {
                throw new Exception("Employee not found with given id");
            }

        }
        return null;
    }


    public EmployeeListResponse getPaginatedEmployeeList(final int page, final int size, final Sort.Direction sort, final Long employeeId) {

        Page<Employee> employees;
        List<EmployeeResponseDTO> employeeList = new ArrayList<>();
        EmployeeListResponse employeeListResponse = new EmployeeListResponse();
        PageRequest pageRequest = PageRequest.of(page, size, sort, "employeeId");
        if (employeeId != null) {
            employees = employeeRepository.findByEmployeeId(employeeId, pageRequest);
        } else {
            employees = employeeRepository.findAll(pageRequest);
        }
        if (employees != null && employees.getContent().size() > 0) {
            employees.getContent().forEach(employeeObj -> {
                EmployeeResponseDTO response = processDto(employeeObj);
                if (response != null) {
                    employeeList.add(response);
                }
            });
        }
        if (employeeList.size() > 0) {
            employeeListResponse.setCurrentPage(page);
            employeeListResponse.setTotalPages(employees.getTotalPages());
            employeeListResponse.setUserLists(employeeList);
            return employeeListResponse;
        }
        return null;
    }


    public EmployeeResponseDTO processDto(final Employee employee) {

        EmployeeResponseDTO employeeResponse = new EmployeeResponseDTO();
        employeeResponse.setAddress(employee.getAddress());
        employeeResponse.setCountry(employee.getCountry());
        employeeResponse.setDepartment(employee.getDepartment());
        employeeResponse.setEmployeeId(employee.getEmployeeId());
        employeeResponse.setFirstName(employee.getFirstName());
        employeeResponse.setLastName(employee.getLastName());
        employeeResponse.setState(employee.getState());
        employeeResponse.setZipCode(employee.getZipCode());
        return employeeResponse;
    }
}
