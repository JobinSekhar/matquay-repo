package com.login.xminds.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * The Employee Entity.
 */
@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    private String firstName;

    private String lastName;

    private String address;

    private String state;

    private String country;

    private String zipCode;

    private String department;

    private LocalDateTime createdOn;

}
