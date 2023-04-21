package com.majors.majorpopulate.service;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majors.majorpopulate.POJO.RegistrationDTO;
import com.majors.majorpopulate.POJO.SectionDTO;
import com.majors.majorpopulate.repository.RegistrationRepository;
import com.majors.majorpopulate.repository.SectionRepository;

@Service
public class SectionServiceImpl implements SectionService{

    @Autowired
    private SectionRepository sectionRepo;
    @Autowired
    private RegistrationRepository rr;
    


    //check all registered courses for date/time
    // return HashTable<date,time> of unique pairs.
    // make call to return sections excluding dates/times in HashTable
    private List<SectionDTO> checkScheduleConflicts(int studentId){
        List<SectionDTO> cleanList = new ArrayList<SectionDTO>();
        List<RegistrationDTO> regList = rr.findByStudentId(studentId);
        Hashtable<List<Date>, List<LocalTime>> dateTime = new Hashtable<>();
        for (RegistrationDTO registrationDTO : regList) {
            var courseId = registrationDTO.getCourseId();

           
        }

        return cleanList;
    }
    
    
    /*
     * Curtis
     * takes the course_term_dates from ResultSet
     * parses it into 2 seperate Dates, course start date
     * and course end date. into a List<Date>
     */
    private List<Date> parseDates(ResultSet result) throws Exception {
        List<Date> dates = new ArrayList<>();
        String dateString = result.getString("course_term_dates");
        if (dateString.isEmpty()) {
            return dates;
        }
        String[] splitDates = dateString.split("-");
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        for (String d : splitDates) {
            var date = format.parse(d.trim());
            dates.add(date);
        }
        return dates;
    }

    /*
     * Curtis
     * takes the course_time from ResultSet
     * parses it into 2 seperate LocalTimes, start time
     * and end time. into a List<LocalTime>
     */
    private List<LocalTime> parseTimes(ResultSet result) throws Exception {
        List<LocalTime> times = new ArrayList<>();
        String timeString = result.getString("course_time");
        if (timeString.isEmpty()) {
            return times;
        }
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        String[] splitTimes = timeString.split("-");
        for (String t : splitTimes) {
            LocalTime time = LocalTime.parse((t).trim(), timeFormatter);
            times.add(time);
        }
        return times;
    }

    @Override
    public Object getSectionsByCourseId(String courseId) {
       return sectionRepo.findAllById(courseId);
    }
}
