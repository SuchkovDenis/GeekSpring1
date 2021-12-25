package ru.gb.springbootdemoapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springbootdemoapp.converter.StudentMapper;
import ru.gb.springbootdemoapp.dto.StudentDto;
import ru.gb.springbootdemoapp.dto.StudentShortDto;
import ru.gb.springbootdemoapp.model.Student;
import ru.gb.springbootdemoapp.service.StudentService;

@RestController
@RequestMapping("/rest")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class StudentRestController {

  private final StudentService studentService;
  private final StudentMapper studentMapper;

  public StudentRestController(StudentService studentService, StudentMapper studentMapper) {
    this.studentService = studentService;
    this.studentMapper = studentMapper;
  }

  @Operation(summary = "Получить всех студентов")
  @GetMapping("/all")
  public List<StudentDto> getAllStudents(@RequestParam(defaultValue = "0") Float score) throws InterruptedException {
    Thread.sleep(3000);
    return studentService.getWithScore(score).stream().map(studentMapper::studentToStudentDto).collect(Collectors.toList());
  }

  @Operation(summary = "Получить подробную информацию о студенте")
  @GetMapping(value = "/info/{id}")
  public ResponseEntity<StudentDto> getStudentInfo(@PathVariable Long id) {
    StudentDto studentDto = studentMapper.studentToStudentDto(studentService.findById(id));
    return new ResponseEntity<>(studentDto, HttpStatus.OK);
  }

  @Operation(summary = "Добавить студента")
  @PostMapping("/add")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Студент успешно добавлен"),
      @ApiResponse(responseCode = "400", description = "Студент не прошел валидацию"),
  })
  public StudentShortDto saveStudent(@Valid @RequestBody StudentShortDto studentShortDto) {
    Student student = studentMapper.studentShortDtoToStudent(studentShortDto);
    studentService.save(student);
    return studentMapper.studentToStudentShortDto(student);
  }
}
