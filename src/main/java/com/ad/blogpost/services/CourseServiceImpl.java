package com.ad.blogpost.services;

import com.ad.blogpost.entities.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    List<Course> courseList;

    public CourseServiceImpl(){
        courseList = new ArrayList<>();
        courseList.add(new Course(1,"Spring Boot", "Java Spring Boot"));
        courseList.add(new Course(2,"Python", "Python Flask"));
        courseList.add(new Course(3,"Javascript", "React"));
    }

    @Override
    public List<Course> getCourses() {
        return courseList;
    }

    @Override
    public Course getCourses(long courseId) {

        Course course=null;
        for (Course c: courseList) {
            if (c.getId()==courseId){
                course = c;
                break;
            }
        }
        return course;
    }

    @Override
    public Course addCourse(Course course) {
        courseList.add(course);
        return course;
    }

    @Override
    public Course updateCourse(Course course) {
        return null;
    }
}
