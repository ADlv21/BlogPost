package com.ad.blogpost.controllers;

import com.ad.blogpost.entities.Course;
import com.ad.blogpost.services.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String hello() {
        return "Welcome To Courses Management";
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return this.courseService.getCourses();
    }

    @GetMapping("/courses/{courseId}")
    public Course getCourse(@PathVariable String courseId) {
        return this.courseService.getCourses(Long.parseLong(courseId));
    }

    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course) {
        return this.courseService.addCourse(course);
    }

    @PutMapping("/courses")
    public Course updateCourse(@RequestBody Course course){
        return this.courseService.updateCourse(course);
    }
}
