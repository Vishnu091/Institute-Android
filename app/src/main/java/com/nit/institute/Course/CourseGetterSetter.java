package com.nit.institute.Course;

/**
 * Created by Aakash on 03-Jun-17.
 */

public class CourseGetterSetter {
    String courseCode;
    String courseName;
    int cIndex;
    public CourseGetterSetter(String code, String name,int columnIndex)
    {
        courseCode=code;
        courseName=name;
        cIndex=columnIndex;
    }
    public String getCourseCode()
    {
        return courseCode;
    }
    public String getCourseName()
    {
        return courseName;
    }
    public int getClmIndex()
    {
        return cIndex;
    }

}
