SELECT e.elective_group, e.course_id as 'Course ID', a.course_title
 from tbl_elective_courses e 
join (select distinct substr(c.course_section,1, 7) as course_id, c.course_title from tbl_courses_offered c) a
on trim(a.course_id) = trim(e.course_id)
where e.elective_id = 2008
order by 2;