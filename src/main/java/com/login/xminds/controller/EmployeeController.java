package com.login.xminds.controller;


import com.login.xminds.dto.EmployeeListResponse;
import com.login.xminds.dto.EmployeeRequestDTO;
import com.login.xminds.dto.EmployeeResponseDTO;
import com.login.xminds.dto.StatusDTO;
import com.login.xminds.service.EmployeeService;
import com.login.xminds.utils.AppResponse;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Employee Apis.
 */
@RestController
@RequestMapping(value = "/api")
public class EmployeeController {

    private EmployeeService employeeService;

    /**
     * The employeeApis.
     *
     * @param employeeServices employeeServices
     */
    public EmployeeController(final EmployeeService employeeServices) {
        this.employeeService = employeeServices;
    }

    @PostMapping (value = "/add-employee")
    public AppResponse<EmployeeResponseDTO> employeeAddOrEdit(@RequestBody final EmployeeRequestDTO request) throws Exception {


        EmployeeResponseDTO response = employeeService.employeeAdd(request);
        if (response != null) {
            return AppResponse.<EmployeeResponseDTO>builder()
                    .data(response)
                    .message("Employee details added successfully!")
                    .success(true)
                    .build();
        } else {
            return AppResponse.<EmployeeResponseDTO>builder()
                    .message("Oops something went wrong, try again later!")
                    .success(false)
                    .build();
        }
    }

    /**
     *
     * @param employeeId employeeId
     * @return StatusDTO
     * @throws Exception Exception
     */
    @DeleteMapping(value = "/delete-employee")
    public AppResponse<StatusDTO> addressEdit(@RequestParam final Long employeeId) throws Exception {


        StatusDTO response = employeeService.deleteEmployee(employeeId);
        if (response != null) {
            return AppResponse.<StatusDTO>builder()
                    .data(response)
                    .message("Employee details deleted successfully!")
                    .success(true)
                    .build();
        } else {
            return AppResponse.<StatusDTO>builder()
                    .message("Oops something went wrong, try again later!")
                    .success(false)
                    .build();
        }
    }


    /**
     *
     * @param page page
     * @param size size
     * @param sort sort
     * @param userId userId
     * @return EmployeeListResponse
     */
    @GetMapping("/employee-list")
    public AppResponse<EmployeeListResponse> getPaginatedUsersList(
            @RequestParam(value = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") final int size,
            @RequestParam(value = "sort", required = false, defaultValue = "ASC") final Sort.Direction sort,
            @RequestParam(value = "employeeId", required = false) final Long userId) {

        EmployeeListResponse employeeListResponse = employeeService.getPaginatedEmployeeList(page, size, sort, userId);
        if (employeeListResponse != null) {
            return AppResponse.<EmployeeListResponse>builder()
                    .data(employeeListResponse)
                    .success(true)
                    .message("Employee List obtained successfully")
                    .build();
        }
        return AppResponse.<EmployeeListResponse>builder()
                .success(false)
                .message("No content based on your search")
                .build();
    }



}
