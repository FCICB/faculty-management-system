package com.fcicb.pdfGenerator;

import com.fcicb.domain.Course;
import com.fcicb.domain.Student;
import com.fcicb.domain.StudentCourse;

import java.util.ArrayList;

public interface GetTranscriptInfo {

    public void queryStudentInfo(int id);

    public void queryCourseInfo(int id);

    public boolean checkStudent(int id);

    public Student getStudent();

    public void setStudent(Student student);

    public ArrayList<StudentCourse> getCourses();

    public void setCourses(ArrayList<StudentCourse> courses);

}
