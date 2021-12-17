package ru.gb.springbootdemoapp.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.springbootdemoapp.dto.StudentDto;
import ru.gb.springbootdemoapp.dto.StudentShortDto;
import ru.gb.springbootdemoapp.converter.StudentConverter;
import static ru.gb.springbootdemoapp.converter.StudentConverter.studentShortDtoToStudent;
import static ru.gb.springbootdemoapp.converter.StudentConverter.studentToStudentDto;
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
    List<StudentDto> students =  studentService.getWithScore(score).stream()
        .map(StudentConverter::studentToStudentDto).collect(Collectors.toList());
    model.addAttribute("studends", students);
    return "student_list";
  }

  // http://localhost:8080/app/info/3 GET
  @GetMapping("/info/{id}")
  public String getStudentInfo(@PathVariable Long id, Model model) {
    model.addAttribute("student", studentToStudentDto(studentService.findById(id)));
    return "student_info";
  }

  // http://localhost:8080/app/add GET
  @GetMapping("/add")
  public String getStudentAddFrom(Model model) {
    StudentShortDto studentShortDto = new StudentShortDto();
    studentShortDto.setName("Имя студента");
    studentShortDto.setScore(100f);

    model.addAttribute("studentShortDto", studentShortDto);
    return "student_form";
  }

  // http://localhost:8080/app/add POST
  @PostMapping("/add")
  public String saveStudent(@Valid StudentShortDto studentShortDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "student_form";
    }
    studentService.save(studentShortDtoToStudent(studentShortDto));
    return "redirect:/";
  }

  // http://localhost:8080/app/delete/3 POST
  @PostMapping("/delete/{id}")
  public String saveStudent(@PathVariable Long id) {
    studentService.deleteById(id);
    return "redirect:/";
  }
}
