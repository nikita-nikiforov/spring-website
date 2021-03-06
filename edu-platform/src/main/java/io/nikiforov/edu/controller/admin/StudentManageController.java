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

    @ResponseBody
    @PostMapping("/admin/add-student")
    public Student addStudent(@RequestBody StudentInfo studentInfo) {
        return studentService.save(studentInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/admin/delete-student", method = RequestMethod.DELETE)
    public String removeStudent(@RequestParam("id") int id) {
        studentService.delete(id);
        return "deleted";
    }

    @GetMapping("/admin/users-manage/students/{id}")
    public String studentPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("student", studentService.getById(id));
        model.addAttribute("groups", groupService.findAll());
        return "admin/users/studentProfile";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/update-student", method = RequestMethod.PUT)
    public Student updateStudent(@RequestBody StudentInfo studentInfo) {
        return studentService.saveWithoutPassword(studentInfo);
    }

    @ResponseBody
    @PostMapping("/admin/update-student-password")
    public String updatePassword(@RequestBody StudentInfo studentInfo) {
        studentService.savePassword(studentInfo);
        return "success";
    }
}