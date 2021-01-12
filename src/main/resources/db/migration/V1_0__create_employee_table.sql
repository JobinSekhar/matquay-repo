CREATE TABLE `employee`(
`employee_id`BIGINT(6) NOT NULL AUTO_INCREMENT,
`first_name` VARCHAR(50) NOT NULL ,
`last_name` VARCHAR(50) NOT NULL ,
`address` VARCHAR(20) NOT NULL ,
`state` VARCHAR(50) NOT NULL,
`country` VARCHAR(200) NOT NULL,
`zip_code` VARCHAR(200) NOT NULL,
`department` ENUM('Accounting', 'Technical', 'Logistics') NOT NULL,
`created_on` DATETIME NOT NULL ,
 PRIMARY KEY (`employee_id`)
 )