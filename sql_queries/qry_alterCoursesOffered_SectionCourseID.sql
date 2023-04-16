SELECT * FROM cpt275_db.tbl_courses_offered;

alter table cpt275_db.tbl_courses_offered
 add course_id varchar(20);
 
 update cpt275_db.tbl_courses_offered
 set course_id = (
 select case when (course_title like '%lab%') then substring(course_section,1,8)
 else substring(course_section,1,7) end);
 
update cpt275_db.tbl_courses_offered
 set section_id = (
 case when (course_title like '%lab%') then substring(course_section, 10,3) else  substring(course_section, 9,3) end);
 
 