package com.majors.majorpopulate;


import java.util.List;
import java.util.Optional;

public record Course (
        List<Section> Classes,
        String CourseName,
        String CourseId,
        List<Course> PreRequisites,
        List<Course> CoRequisites
) {

}
 