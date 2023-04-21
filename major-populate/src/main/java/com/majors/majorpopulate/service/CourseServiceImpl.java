package com.majors.majorpopulate.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majors.majorpopulate.Course;
import com.majors.majorpopulate.Section;
import com.majors.majorpopulate.POJO.CoReq;
import com.majors.majorpopulate.POJO.CourseDTO;
import com.majors.majorpopulate.POJO.ElectiveCourses;
import com.majors.majorpopulate.POJO.Grade;
import com.majors.majorpopulate.POJO.PreReq;
import com.majors.majorpopulate.POJO.RegistrationDTO;
import com.majors.majorpopulate.repository.CoReqRepository;
import com.majors.majorpopulate.repository.CourseDTORepository;
import com.majors.majorpopulate.repository.ElectiveCourseRepository;
import com.majors.majorpopulate.repository.GradeRepository;
import com.majors.majorpopulate.repository.PreReqRepository;
import com.majors.majorpopulate.repository.RegisitrationRepository;

@Service
public class CourseServiceImpl implements CourseService{
    
    @Autowired
    private PreReqRepository preReqRepo;
    @Autowired
    private CoReqRepository coReqRepo;
    @Autowired
    private CourseDTORepository courseRepo;
    @Autowired
    private ElectiveCourseRepository electiveCourseRepo;
    @Autowired
    private GradeRepository gradeRepo;
    @Autowired
    private RegisitrationRepository registerRepo;

    @Override
    public void saveCourse(Course tblCourseCatalog) {
        this.courseRepo.save(tblCourseCatalog);
    }

    @Override
    public List<PreReq> getPreReqsByCourseId(String course_id) {
        List<PreReq> pre_reqs;
        pre_reqs = preReqRepo.findByCourseId(course_id);
        return pre_reqs;
    }

    @Override
    public List<CoReq> getCoReqsByCourseId(String course_id) {
        List<CoReq> co_reqs;
        co_reqs = coReqRepo.findByCourseId(course_id);
        return co_reqs;
    }
   
    @Override
    public List<CourseDTO> findByCourseId(String course_id) {
        List<CourseDTO> course;
        course = courseRepo.findByCourseId(course_id);
        return course;
    }

    @Override
    public List<Section> getSectionByCourseId(String course_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSectionByCourseId'");
    }

    @Override
    public List<CourseDTO> findByElectiveGroupId(int groupId, int studentId) {
        List<ElectiveCourses> electiveCourseList = electiveCourseRepo.findByElectiveGroupId(groupId);
        List<CourseDTO> courseList= new ArrayList<CourseDTO>();
        for (ElectiveCourses electiveCourses : electiveCourseList) {
            var courseId = electiveCourses.getCourseId();
            List<CourseDTO> course = courseRepo.findByCourseId(courseId.trim());
            for (CourseDTO c : course) {
                getRegistrationStatus(c, studentId);
                c.setPreReqCheck(getPreReqCheck(c, studentId));
            }
           courseList.addAll(course); 
        }
        return courseList;
    }

    private void getRegistrationStatus(CourseDTO course, int studentId){
        List<Grade> grade = gradeRepo.findByCourseIdAndStudentId(course.getCourseId(), studentId);
        String status;
        String registeredStatus;
            if(grade.size() != 0) {
                status = grade.get(0).getCourseStatus();
                course.setStatus(status); 
                course.setGrade(grade.get(0).getCourseGrade());
            } else {
                List<RegistrationDTO> registration = registerRepo.findByCourseIdAndStudentId(course.getCourseId(), studentId);
                if (registration.size() != 0){
                    registeredStatus = registration.get(0).getRegDTS();
                    course.setRegisteredStatus(registeredStatus);
                    course.setStatus("In Progress");
                } else {
                    course.setRegisteredStatus("Not Registered");
                    course.setStatus("Not Complete");
                }
            } 
}
    private boolean getPreReqCheck(CourseDTO course, int studentId) {
        List<PreReq> preReqForCourse = preReqRepo.findByCourseId(course.getCourseId());
        boolean localPreReqCheck = true;
        int complete = 0;
        int registered = 0;
        ArrayList<String> termList = new ArrayList<String>();
        for (int p = 0; p < preReqForCourse.size(); p++) {
            List<Grade> checkForPreReqGrade = gradeRepo.findByCourseIdAndStudentId(preReqForCourse.get(p).getPre_req(), studentId);
            if(checkForPreReqGrade.size() == 0){
                System.out.println("no Grade");
                List<RegistrationDTO> checkRegistrationTerm = registerRepo.findByCourseIdAndStudentId(preReqForCourse.get(p).getPre_req(), studentId);
                if(checkRegistrationTerm.size() != 0){
                    System.out.println(checkRegistrationTerm.get(0).getTerm());
                    //course.setPreReqRegistered(true);
                    // course.setStatus("Pre Req Registered");
                    //course.setPreReqTerm(checkRegistrationTerm.get(0).getTerm());
                    termList.add(checkRegistrationTerm.get(0).getTerm());
                    registered++;
                } 
                else return false;
            } else {
                System.out.println(checkForPreReqGrade.get(0).getCourseGrade());
                complete++;
            }    
        }
        if(complete + registered != preReqForCourse.size()){
            course.setPreReqRegistered(false); 
        } else if(registered > 0){
            System.out.println(termList);
            termList.sort(Comparator.comparingInt(MajorService.termListOrder::indexOf));
            System.out.println(termList);
            if(termList.size() <= 1){
                course.setPreReqTerm(termList.get(0));
            } else {
                course.setPreReqTerm(termList.get(termList.size() -1));
            }
        }
        else {
            course.setPreReqRegistered(true);
        }     
        return localPreReqCheck; 
    }
    /*
     * stephen added 4/3 
     */
    @Override
    public List<CourseDTO> getNameByCourseId(String course_id) {
        List<CourseDTO> courseName;
        courseName = courseRepo.findByCourseId(course_id);
        return courseName;
        
    }
}
