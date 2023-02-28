package com.majors.majorpopulate;

import java.util.List;

public record Course(
                List<EachClass> Classes,
                String CourseName,
                String CourseId,
                List<Course> PreRequisites,
                List<Course> CoRequisites) {
}
// prueba
