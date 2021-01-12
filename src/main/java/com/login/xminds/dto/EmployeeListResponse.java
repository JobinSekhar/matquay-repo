package com.login.xminds.dto;

import lombok.Data;

import java.util.List;

/**
 * The EmployeeListResponse.
 */
@Data
public class EmployeeListResponse {

    private int totalPages;

    private int currentPage;

    private List<EmployeeResponseDTO> userLists;


}
