package ru.gb.springbootdemoapp.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springbootdemoapp.converter.StudentConverter;
import static ru.gb.springbootdemoapp.converter.StudentConverter.studentShortDtoToStudent;
import static ru.gb.springbootdemoapp.converter.StudentConverter.studentToStudentDto;
import static ru.gb.springbootdemoapp.converter.StudentConverter.studentToStudentShortDto;
import ru.gb.springbootdemoapp.dto.StudentDto;
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
  public List<StudentDto> getAllStudents(@RequestParam(defaultValue = "0") Float score, HttpServletResponse response) {
    response.setStatus(HttpServletResponse.SC_ACCEPTED);
    response.setHeader("set-cookie", "happy-number=777");
    return studentService.getWithScore(score).stream().map(StudentConverter::studentToStudentDto).collect(Collectors.toList());
  }

  // http://localhost:8080/app/rest/info/3 GET
  @GetMapping(value = "/info/{id}")
  public ResponseEntity<StudentDto> getStudentInfo(@PathVariable Long id) {
    StudentDto studentDto = studentToStudentDto(studentService.findById(id));
    HttpHeaders headers = new HttpHeaders();
    headers.add("abc", "123");
    return new ResponseEntity<>(studentDto, headers, HttpStatus.OK);
  }

  // http://localhost:8080/app/rest/add POST
  @PostMapping("/add")
  public StudentShortDto saveStudent(@Valid @RequestBody StudentShortDto studentShortDto) {
    Student student = studentShortDtoToStudent(studentShortDto);
    studentService.save(student);
    return studentToStudentShortDto(student);
  }
}
