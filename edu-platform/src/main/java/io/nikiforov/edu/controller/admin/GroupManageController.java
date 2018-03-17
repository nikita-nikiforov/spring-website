package io.nikiforov.edu.controller.admin;

import io.nikiforov.edu.entity.Group;
import io.nikiforov.edu.model.GroupInfo;
import io.nikiforov.edu.service.GroupService;
import io.nikiforov.edu.service.SpecialtyService;
import io.nikiforov.edu.service.StudentService;
import io.nikiforov.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GroupManageController {

    @Autowired
    GroupService groupService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    SpecialtyService specialtyService;

    @GetMapping("/admin/groups-manage")
    public String groupsManagePage(Model model) {
        model.addAttribute("newGroup", new GroupInfo());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("specialties", specialtyService.findAll());
        return "admin/groups/groupsManage";
    }

    @PostMapping("/admin/groups-manage/add-group")
    public String addGroup(@ModelAttribute("newGroup") GroupInfo modelGroup) {
        groupService.save(new Group(modelGroup));
        return "redirect:/admin/groups-manage";
    }

    @GetMapping("/admin/groups-manage/group-{id}")
    public String groupPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("group", groupService.getGroup(id));
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("students", studentService.getByGroupId(id));
        return "admin/groups/groupManage";
    }

    @PostMapping("/admin/groups-manage/update")
    public String updateGroup(@ModelAttribute("group") Group group) {
        groupService.update(group);
        return "redirect:/admin/groups-manage/group-" + group.getId();
    }

    @GetMapping("/admin/groups-manage/delete-group")
    public String deleteGroup(@RequestParam("id") int id) {
        groupService.delete(id);
        return "redirect:/admin/groups-manage";
    }
}
