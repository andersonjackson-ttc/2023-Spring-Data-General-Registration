package com.majors.majorpopulate;

import java.util.Date;

public record Course (
        List<Section> Classes,
        String CourseName,
        String CourseId,
        List<Course> PreRequisites,
        List<Course> CoRequisites
) {
        
}
 