package io.nikiforov.edu.controller;

import java.io.IOException;
import java.util.List;

import io.nikiforov.edu.entity.Course;
import io.nikiforov.edu.entity.Lecture;
import io.nikiforov.edu.entity.LectureFile;
import io.nikiforov.edu.entity.LecturePDFFile;
import io.nikiforov.edu.model.LabWorkInfo;
import io.nikiforov.edu.model.LectureInfo;
import io.nikiforov.edu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private LectureService lectureService;

    @Autowired
    private LabWorkService labWorkService;

    @Autowired
    private LectureFileService lectureFileService;

    @Autowired
    private LecturePDFFileService lecturePDFFileService;

    @RequestMapping("/all-courses")
    public String mainPage(HttpServletRequest request) {
        request.setAttribute("courses", courseService.getAllCourses());
        return "student/coursesPage";
    }

    @GetMapping("/course-{id}")
    public String coursePage(@PathVariable int id, Model model){
        model.addAttribute("course", courseService.getCourse(id));
        model.addAttribute("lectures", lectureService.getAllLectures(id));
        model.addAttribute("labWorks", labWorkService.findAllByCourseId(id));
        return "student/coursePage";
    }
}