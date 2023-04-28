CREATE TABLE `cpt275_db`.`tbl_registration` (
  `student_id` INT NOT NULL,
  `major_id` VARCHAR(50) NULL,
  `course_id` VARCHAR(50) NULL,
  `section_id` VARCHAR(50) NULL,
  `reg_dts` TIMESTAMP(2) NULL,
  INDEX `student_id_reg_idx` (`student_id` ASC),
  CONSTRAINT `reg_student_id`
    FOREIGN KEY (`student_id`)
    REFERENCES `cpt275_db`.`tbl_student_profile` (`student_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);