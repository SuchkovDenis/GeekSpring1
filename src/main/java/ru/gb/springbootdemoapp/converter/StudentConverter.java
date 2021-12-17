package ru.gb.springbootdemoapp.converter;

import java.util.Optional;
import ru.gb.springbootdemoapp.dto.StudentDto;
import ru.gb.springbootdemoapp.dto.StudentShortDto;
import ru.gb.springbootdemoapp.model.Faculty;
import ru.gb.springbootdemoapp.model.Student;

public class StudentConverter {

  public static StudentShortDto studentToStudentShortDto(Student student) {
    if (student == null) {
      return null;
    }
    StudentShortDto studentShortDto = new StudentShortDto();
    studentShortDto.setName(student.getName());
    studentShortDto.setScore(student.getScore());

    return studentShortDto;
  }

  public static Student studentShortDtoToStudent(StudentShortDto studentShortDto) {
    if (studentShortDto == null) {
      return null;
    }
    Student student = new Student();
    student.setName(studentShortDto.getName());
    student.setScore(studentShortDto.getScore());

    return student;
  }

  public static StudentDto studentToStudentDto(Student student) {
    if (student == null) {
      return null;
    }
    StudentDto studentDto = new StudentDto();
    studentDto.setId(student.getId());
    studentDto.setName(student.getName());
    Optional.ofNullable(student.getScore())
        .map(Object::toString).ifPresent(studentDto::setScore);
    Optional.ofNullable(student.getFaculty())
        .map(Faculty::getTitle).ifPresent(studentDto::setFacultyName);

    return studentDto;
  }
}
