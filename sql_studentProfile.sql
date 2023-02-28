CREATE TABLE `cpt275_db`.`tbl_student_profile` (
  `student_id` INT NOT NULL,
  `student_first_name` VARCHAR(50) NULL,
  `student_last_name` VARCHAR(50) NULL,
  `student_email` VARCHAR(255) NULL,
  `major_id` VARCHAR(45) NULL,
  PRIMARY KEY (`student_id`));