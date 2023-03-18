package com.majors.majorpopulate;

import java.util.List;

public record Course (
        List<Section> Classes,
        String CourseName,
        String CourseId,
        List<Course> PreRequisites,
        List<Course> CoRequisites,
        String Status
) {
        public Course withStatus(String status) {
                return new Course( Classes(), CourseName, CourseId, PreRequisites(), CoRequisites(), status);
            }

}
 