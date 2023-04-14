CREATE TABLE `cpt275_db`.`tbl_student_login` (
  `student_id` INT NOT NULL,
  `student_password` VARCHAR(50) NULL,
  `password_expiration` TIMESTAMP(2) NULL,
  `password_hint_q1` VARCHAR(50) NULL,
  `password_hint_a1` VARCHAR(45) NULL,
  `password_hint_q2` VARCHAR(45) NULL,
  `password_hint_a2` VARCHAR(45) NULL,
  INDEX `student_id_login_idx` (`student_id` ASC),
  CONSTRAINT `login_student_id`
    FOREIGN KEY (`student_id`)
    REFERENCES `cpt275_db`.`tbl_student_profile` (`student_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);