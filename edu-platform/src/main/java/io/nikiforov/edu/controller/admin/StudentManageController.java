package io.nikiforov.edu.controller.admin;

import io.nikiforov.edu.entity.Student;
import io.nikiforov.edu.model.StudentInfo;
import io.nikiforov.edu.service.GroupService;
import io.nikiforov.edu.service.RoleService;
import io.nikiforov.edu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentManageController {

    @Autowired
    StudentService studentService;

    @Autowired
    GroupService groupService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/admin/users-manage/students")
    public String studentManage(Model model) {
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("newStudent", new Student());
        return "admin/users/usersManageStudents";
    }

    @PostMapping("/admin/users-manage/students/add-student")
    public String addUser(@ModelAttribute("newStudent") StudentInfo studentInfo) {
        studentService.save(studentInfo);
        return "redirect:/admin/users-manage/students";
    }

    @GetMapping("/admin/users-manage/students/delete-student")
    public String deleteStudent(@RequestParam("id") int id) {
        studentService.delete(id);
        return "redirect:/admin/users-manage/students";
    }

    @GetMapping("/admin/users-manage/students/{id}")
    public String studentPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("student", studentService.getById(id));
        model.addAttribute("groups", groupService.findAll());
        System.out.println(groupService.findAll());
        return "admin/users/studentProfile";
    }

    @PostMapping("/admin/users-manage/students/update")
    public String updateStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:/admin/users-manage/students/" + student.getId();
    }
}