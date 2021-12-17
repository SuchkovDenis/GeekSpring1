package ru.gb.springbootdemoapp.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static ru.gb.springbootdemoapp.converter.StudentConverter.studentShortDtoToStudent;
import static ru.gb.springbootdemoapp.converter.StudentConverter.studentToStudentDto;
import static ru.gb.springbootdemoapp.converter.StudentConverter.studentToStudentShortDto;
import ru.gb.springbootdemoapp.dto.StudentDto;
import ru.gb.springbootdemoapp.converter.StudentConverter;
import ru.gb.springbootdemoapp.dto.StudentShortDto;
import ru.gb.springbootdemoapp.model.Student;
import ru.gb.springbootdemoapp.service.StudentService;

@RestController
@RequestMapping("/rest")
public class StudentRestController {

  private final StudentService studentService;

  public StudentRestController(StudentService studentService) {
    this.studentService = studentService;
  }

  // http://localhost:8080/app/rest/all GET
  @GetMapping("/all")
  public List<StudentDto> getAllStudents(@RequestParam(defaultValue = "0") Float score) {
    return studentService.getWithScore(score).stream().map(StudentConverter::studentToStudentDto).collect(Collectors.toList());
  }

  // http://localhost:8080/app/rest/info/3 GET
  @GetMapping("/info/{id}")
  public StudentDto getStudentInfo(@PathVariable Long id) {
    return studentToStudentDto(studentService.findById(id));
  }

  // http://localhost:8080/app/rest/add POST
  @PostMapping("/add")
  public StudentShortDto saveStudent(@RequestBody StudentShortDto studentShortDto) {
    Student student = studentShortDtoToStudent(studentShortDto);
    studentService.save(student);
    return studentToStudentShortDto(student);
  }
}
