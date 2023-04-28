package com.majors.majorpopulate.service;

import java.util.ArrayList;
import java.util.Comparator;
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
/*
     //Checking status of registered course
     public String checkTranscriptStatus (int studentId, String courseId){
        String rtnval;
        List<Grade> studentTranscript = gradeRepo.findByCourseIdAndStudentId(courseId, studentId);
        String courseStatus = studentTranscript.get(4).toString();
        //String courseGrade = studentTranscript.get(3).toString();
        rtnval = courseStatus;
        return rtnval;
    }

    //Checks grade of completed course
    public String checkCourseGrade (int studentId, String courseId){
        String rtnval;
        List<Grade> studentTranscript = gradeRepo.findByCourseIdAndStudentId(courseId, studentId);
        String courseGrade = studentTranscript.get(3).toString();
        rtnval = courseGrade;
        return rtnval;
    }
        
    //Return list of courses that are now available for signup based on student transcript. 
    //      IF "in progress" || "complete"  it opens classes for signup
    public List<PreReq> openPreReqs (String courseId, int studentId){
        // doesnt check transcript for IP and COMPLETES
        //adds to list for rtnval
        //check for duplicates
        List<PreReq> rtnval = new ArrayList<>();
        List<Student> studentInfo = studentRepo.findMajorByStudentId(studentId);
        String studentMajor = studentInfo.get(3).toString();
        List<GradRequirements> gradReqs = gradReqRepo.findAllByMajorName(studentMajor); // Holds reqs for students major
        List<Grade> studentTranscript = gradeRepo.findByCourseIdAndStudentId(courseId, studentId); //Holds all classes in student current transcript
        List<PreReq> preReqs;
        String check;
        boolean gradNeed = false;
        for(int i = 0; i < studentTranscript.size(); i++){
            Grade course = studentTranscript.get(i);
            // need to check prereqs against only major grad reqs
            preReqs = preReqRepo.findByPreReq(course.getCourseId()); //Local compare of course to ALL prereqs
            for(int j = 0; j < preReqs.size(); j++){
                //compare prereqs to gradreqs then add to rtnval with no duplicates
                check = preReqs.get(j).getCourseId();
                for(int k = 0; k <gradReqs.size();k++){
                    if(check.compareTo(gradReqs.get(k).getCourseId()) == 0){ //courseId is equal to grad req ID
                        gradNeed = true;
                        //Check to make sure its not in rtnval already
                        //rtnval.add(preReqs.get(j));
                        break; //No need to continue thru grad reqs -- jump to next prereq
                    }
                }
                if(gradNeed){
                    boolean duplicate = false;
                    if(rtnval.size() == 0) //first addition
                        rtnval.add(preReqs.get(j));
                    else{
                        for(int z = 0; z < rtnval.size(); z++){
                            if(check.compareTo(rtnval.get(z).getCourseId()) == 0){
                                duplicate = true;
                            }
                        }
                        if (!duplicate)
                            rtnval.add(preReqs.get(j));
                    }
                }
            }
        }
        return rtnval;
    }
 */
}
