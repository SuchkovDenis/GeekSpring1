package ru.gb.springbootdemoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.springbootdemoapp.model.Student;
import ru.gb.springbootdemoapp.service.StudentService;

@Controller
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  // http://localhost:8080/app/ GET
  @GetMapping("/")
  public String getAllStudents(Model model, @RequestParam(defaultValue = "0") Float score) {
    model.addAttribute("studends", studentService.getWithScore(score));
    return "student_list";
  }

  // http://localhost:8080/app/info/3 GET
  @GetMapping("/info/{id}")
  public String getStudentInfo(@PathVariable Long id, Model model) {
    model.addAttribute("student", studentService.findById(id));
    return "student_info";
  }

  // http://localhost:8080/app/add GET
  @GetMapping("/add")
  public String getStudentAddFrom() {
    return "student_form";
  }

  // http://localhost:8080/app/add POST
  @PostMapping("/add")
  public String saveStudent(Student student) {
    studentService.save(student);
    return "redirect:/";
  }

  // http://localhost:8080/app/delete/3 POST
  @PostMapping("/delete/{id}")
  public String saveStudent(@PathVariable Long id) {
    studentService.deleteById(id);
    return "redirect:/";
  }
}
