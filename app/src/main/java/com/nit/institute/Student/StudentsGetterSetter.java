package com.nit.institute.Student;

/**
 * Created by Aakash on 01-Jun-17.
 */

public class StudentsGetterSetter {
    String reg;
    String studentName;
    boolean x=false;
    String courseid;

    public StudentsGetterSetter(String rg,String courseId,String sName){
        reg=rg;
        studentName=sName;
        courseid=courseId;
    }

    public void setAttendanceStatus(boolean st)
    {
        x=st;
    }
    public boolean getAttendanceStatus(){return x;}

    public String getStudentName() {
        return studentName;
    }
    public String getReg(){
        return reg;
    }
    public String getCourseid(){
        return courseid;
    }

}