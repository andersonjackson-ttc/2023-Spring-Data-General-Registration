package com.majors.majorpopulate.POJO;

import lombok.*;

@Getter
@Setter
public class RegisteredSection {
    private String majorId;
    private String courseId;
    private String sectionId;
    private String term;


    public RegisteredSection() {
    }

    public RegisteredSection(String majorId, String courseId, String sectionId, String term) {
        this.majorId = majorId;
        this.courseId = courseId;
        this.sectionId = sectionId;
        this.term = term;
    }

}
