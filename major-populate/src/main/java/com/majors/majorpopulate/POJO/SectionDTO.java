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
    private String courseId;
    @Column(name = "course_section")
    private String courseSection;
    @Column(name = "course_days")
    private String courseDays;
    @Column(name = "course_term") @NonNull
    private String courseTerm;
    @Column(name = "course_term_dates") @NonNull
    private List<Date> courseTermDates;
    @Column(name = "course_time")
    private List<LocalTime> courseTime;
    @Column(name = "course_location")
    private String courseLocation;
    @Column(name = "course_building_nbr")
    private String courseBuildingNum;
    @Column(name = "course_room")
    private String courseRoomNum;
    @Column(name = "course_type") @NonNull
    private String courseType;
    @Column(name = "seats_taken") 
    private int seatsTaken;
    @Column(name = "total_seats") 
    private int seatsTotal;

}
