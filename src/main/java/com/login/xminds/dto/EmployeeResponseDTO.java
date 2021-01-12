package com.login.xminds.dto;

import lombok.Data;


/**
 * The EmployeeResponseDTO.
 */
@Data
public class EmployeeResponseDTO {

    private Long employeeId;

    private String firstName;

    private String lastName;

    private String address;

    private String state;

    private String country;

    private String zipCode;

    private String department;
}
