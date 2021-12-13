package ru.gb.springbootdemoapp.dto;

import java.util.Optional;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.springbootdemoapp.model.Faculty;
import ru.gb.springbootdemoapp.model.Student;

@Data
@NoArgsConstructor
public class StudentDto {
  private String name;
  private String score;
  private String facultyName;

  public StudentDto(Student student) {
    name = student.getName();
    score = student.getScore().toString();
    facultyName = Optional.ofNullable(student.getFaculty())
        .map(Faculty::getTitle).orElse(null);
  }
}
