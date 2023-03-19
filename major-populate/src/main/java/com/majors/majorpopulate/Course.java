package com.majors.majorpopulate;

import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
        List<Section> Classes;
        String CourseName;
        String CourseId;
        List<Course> PreRequisites;
        List<Course> CoRequisites;
        String Status;
        String Grade;
}
 