select distinct pr.course_id as 'Course ID' , a.course_title as 'Course Title'
, pr.prereq as 'Pre Req', a1.course_title
from tbl_pre_reqs pr 
join (select distinct substr(c.course_section,1, 7) as course_id, c.course_title from tbl_courses_offered c) a
on trim(a.course_id) = trim(pr.course_id)
join (select distinct substr(c.course_section,1, 7) as course_id, c.course_title from tbl_courses_offered c) a1
on trim(a1.course_id) = trim(pr.prereq)
where pr.course_id = 'acc-101'
order by 2;