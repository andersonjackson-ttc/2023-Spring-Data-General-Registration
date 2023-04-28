CREATE TABLE `tbl_course_catalog` (
  `course_id` varchar(10) DEFAULT NULL,
  `course_title` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into  cpt275_db.tbl_course_catalog
(course_id, course_title)
SELECT distinct 
case when (course_title like '%lab%') then concat(substr(course_section,1,7),'L') else substr(course_section,1,7) end course_id, 
course_title FROM cpt275_db.tbl_courses_offered
;