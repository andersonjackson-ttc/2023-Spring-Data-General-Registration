CREATE TABLE `cpt275_db`.`tbl_student_transcript` (
  `student_id` INT NOT NULL,
  `term_id` VARCHAR(45) NULL,
  `course_id` VARCHAR(45) NULL,
  `course_grade` VARCHAR(10) NULL,
  `course_status` VARCHAR(50) NULL,
  INDEX `student_id_transcript_idx` (`student_id` ASC),
  CONSTRAINT `transcript_student_id`
    FOREIGN KEY (`student_id`)
    REFERENCES `cpt275_db`.`tbl_student_profile` (`student_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);