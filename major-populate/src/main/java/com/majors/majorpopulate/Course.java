package com.majors.majorpopulate;

import java.util.List;


public record Course (
        List<EachClass> Classes,
        String MajorName,
        String CourseName,
        String CourseId,
        List<EachClass> PreRequisites,
        List<EachClass> CoRequisites
) {
}
