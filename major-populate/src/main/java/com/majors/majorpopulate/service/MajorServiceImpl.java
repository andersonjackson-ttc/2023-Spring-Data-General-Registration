package com.majors.majorpopulate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.majors.majorpopulate.Course;
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
    @Autowired 
    private CourseService cs;

    @Override
    public List<MajorDTO> findAll() {
        List<MajorDTO> majors;
        majors = majorRepo.findAll();
        return majors;
    }
    
    @Override
    public void save(RegistrationDTO newRegisteredSection) {
       registerRepo.save(newRegisteredSection);
    }

    @Override
    public List<CourseDTO> findAllCoursesByMajorName(String majorName, int studentId) {
        List<GradRequirements> requiredCourseList;
        requiredCourseList = gradReqRepo.findAllByMajorName(majorName);
        List<CourseDTO> courseList =  getCourseBuild(requiredCourseList, studentId);
        return courseList; 
    }
    
    private List<CourseDTO> getCourseBuild(List<GradRequirements> requiredCourseList, int studentId) {
        List<CourseDTO> localCourseList = new ArrayList<>();
        for(int i = 0; i < requiredCourseList.size();i++)
        {
            String courseId = requiredCourseList.get(i).getCourseId().trim();
            List<CourseDTO> course = courseRepo.findByCourseId(courseId);
            List<Grade> grade = gradeRepo.findByCourseIdAndStudentId(courseId, studentId);
            String status;
            String registeredStatus;
            if(course.size() == 0){
                CourseDTO emptyCourseName = new CourseDTO();

                emptyCourseName.setCourseId(courseId);
                emptyCourseName.setCourseTitle(courseId);
                emptyCourseName.setStatus("No Course This Term");
                emptyCourseName.setRegisteredStatus("Not Registered");
                localCourseList.add(emptyCourseName);
                continue;
            }
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
            course.get(0).setPreReqCheck(getPreReqCheck(course.get(0), courseId, studentId));
            // // if (course.size() == 0) continue;
            // else{
                localCourseList.add(course.get(0));
            // }
        }
        return localCourseList;
    }
    
    private boolean getPreReqCheck(CourseDTO course, String courseId, int studentId) {
        List<PreReq> preReqForCourse = preReqRepo.findByCourseId(courseId);
        boolean localPreReqCheck = true;
        for (int p = 0; p < preReqForCourse.size(); p++) {
            List<Grade> checkForPreReqGrade = gradeRepo.findByCourseIdAndStudentId(preReqForCourse.get(p).getPre_req(), studentId);
            if(checkForPreReqGrade.size() == 0){
                System.out.println("no Grade");
                return false; 
            } else {
                System.out.println(checkForPreReqGrade.get(0).getCourseGrade());
            }         
        }
        return localPreReqCheck; 
    }

    @Override
    public List<MajorElectives> findElectGroupsInMajor(String majorName, int studentId) {
        List<MajorElectives> electiveGroups = mERepo.findAllByMajorName(majorName);
        for (MajorElectives group : electiveGroups) {
            int numCompleted = 0;
            int numberRegistered = 0;
           
            List<CourseDTO> courses = cs.findByElectiveGroupId(group.getElectiveGroupId(), studentId);
            for (CourseDTO course : courses) {
                if (course.getStatus().equalsIgnoreCase("complete")){
                   numCompleted++; 
                }
                if (course.getRegisteredStatus() != null && course.getRegisteredStatus().equalsIgnoreCase("registered")){
                    numberRegistered++;
                }
            }
            group.setNumberRegistered(numberRegistered);
            group.setNumCompleted(numCompleted);
        }
        return electiveGroups;
    }

 
    
    
    
    
}
