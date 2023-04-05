package com.majors.majorpopulate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majors.majorpopulate.Course;
import com.majors.majorpopulate.POJO.CourseDTO;
import com.majors.majorpopulate.POJO.GradRequirements;
import com.majors.majorpopulate.POJO.Grade;
import com.majors.majorpopulate.POJO.MajorDTO;
import com.majors.majorpopulate.POJO.MajorElectives;
import com.majors.majorpopulate.POJO.PreReq;
import com.majors.majorpopulate.POJO.RegistrationDTO;
import com.majors.majorpopulate.repository.CourseDTORepository;
import com.majors.majorpopulate.repository.GradReqRepository;
import com.majors.majorpopulate.repository.GradeRepository;
import com.majors.majorpopulate.repository.MajorDTORepository;
import com.majors.majorpopulate.repository.MajorElectRepository;
import com.majors.majorpopulate.repository.PreReqRepository;
import com.majors.majorpopulate.repository.RegisitrationRepository;

@Service
public class MajorServiceImpl implements MajorService2{

    @Autowired
    private MajorDTORepository majorRepo;
    @Autowired
    private GradReqRepository gradReqRepo;
    @Autowired
    private CourseDTORepository courseRepo;
    @Autowired
    private GradeRepository gradeRepo;
    @Autowired 
    private RegisitrationRepository registerRepo;
    @Autowired
    private PreReqRepository preReqRepo;
    @Autowired
    private MajorElectRepository mERepo;

    @Override
    public List<MajorDTO> findAll() {
        List<MajorDTO> majors;
        majors = majorRepo.findAll();
        return majors;
    }

    @Override
    public List<CourseDTO> findAllCoursesByMajorName(String majorName, int studentId) {
        List<GradRequirements> requiredCourseList;
        List<CourseDTO> courseList =  new ArrayList<CourseDTO>();
        requiredCourseList = gradReqRepo.findAllByMajorName(majorName);
        for(int i = 0; i < requiredCourseList.size();i++)
        {
            String courseId = requiredCourseList.get(i).getCourseId().trim();
            List<CourseDTO> course = courseRepo.findByCourseId(courseId);
            List<Grade> grade = gradeRepo.findByCourseIdAndStudentId(courseId, studentId);
            String status;
            String registeredStatus;
            if(grade.size() != 0) {
                status = grade.get(0).getCourseStatus();
                course.get(0).setStatus(status); 
                course.get(0).setGrade(grade.get(0).getCourseGrade());
            } else {
                List<RegistrationDTO> registration = registerRepo.findByCourseIdAndStudentId(courseId, studentId);
                if (registration.size() != 0){
                    registeredStatus = registration.get(0).getRegDTS();
                    course.get(0).setRegisteredStatus(registeredStatus);
                    course.get(0).setStatus("In Progress");
                } else {
                    course.get(0).setRegisteredStatus("Not Registered");
                    course.get(0).setStatus("Not Complete");
                }
            } 
            List<PreReq> preReqForCourse = preReqRepo.findByCourseId(courseId);
            for (int p = 0; p < preReqForCourse.size(); p++) {
                course.get(0).setPreReqCheck(true);
                List<Grade> checkForPreReqGrade = gradeRepo.findByCourseIdAndStudentId(preReqForCourse.get(p).getPre_req(), studentId);
                if(checkForPreReqGrade.size() == 0){
                    System.out.println("no Grade");
                    course.get(0).setPreReqCheck(false);
                    break;
                } else {
                    System.out.println(checkForPreReqGrade.get(0).getCourseGrade());
                }         
            }
            if (course.size() == 0) continue;
            else{
                courseList.add(course.get(0));
            }
        }
        return courseList;
    }

    @Override
    public void save(RegistrationDTO newRegisteredSection) {
       registerRepo.save(newRegisteredSection);
    }

    @Override
    public List<MajorElectives> findElectGroupsInMajor(String majorName) {
        return mERepo.findAllByMajorName(majorName);
    }

 
    
}
