package com.majors.majorpopulate.POJO;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_courses_offered")
public class SectionDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int entry;

    @Column(name = "course_id") @NonNull
    private String CourseId;
    @Column(name = "course_section")
    private String CourseSection;
    @Column(name = "course_days")
    private String CourseDays;
    @Column(name = "course_term") @NonNull
    private String CourseTerm;
    @Column(name = "course_term_dates") @NonNull
    private List<Date> CourseTermDates;
    @Column(name = "course_time")
    private List<LocalTime> CourseTime;
    @Column(name = "course_location")
    private String CourseLocation;
    @Column(name = "course_building_nbr")
    private String CourseBuildingNum;
    @Column(name = "course_room")
    private String CourseRoomNum;
    @Column(name = "course_type") @NonNull
    private String CourseType;
    @Column(name = "seats_taken") 
    private int SeatsTaken;
    @Column(name = "total_seats") 
    private int SeatsTotal;

}
