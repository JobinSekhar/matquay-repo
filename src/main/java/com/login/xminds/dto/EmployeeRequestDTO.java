package com.login.xminds.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * The EmployeeRequestDTO.
 */
@Data
public class EmployeeRequestDTO {


    private Long employeeId;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String address;

    @NotNull
    @NotEmpty
    private String state;

    @NotNull
    @NotEmpty
    private String country;

    @NotNull
    @NotEmpty
    private String zipCode;

    @NotNull
    @NotEmpty
    private String department;

}
