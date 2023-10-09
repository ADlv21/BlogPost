package com.ad.blogpost.services;

import com.ad.blogpost.entities.Course;

import java.util.List;

public interface CourseService {
    List<Course> getCourses();

    Course getCourses(long courseId);

    Course addCourse(Course course);

    Course updateCourse(Course course);
}
