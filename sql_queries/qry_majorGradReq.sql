select distinct g.major_id  'Major Id', g.major_name as 'Major Name', 
				g.req_type as 'Requirment type', g.course_id as 'Course ID'
                , a.course_title
from tbl_grad_requirement g 
join (select distinct substr(c.course_section,1, 7) as course_id, c.course_title from tbl_courses_offered c) a
on trim(a.course_id) = trim(g.course_id)
where g.major_id = 1011
order by 3;


