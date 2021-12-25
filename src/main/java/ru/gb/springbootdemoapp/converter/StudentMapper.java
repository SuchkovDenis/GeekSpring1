package ru.gb.springbootdemoapp.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gb.springbootdemoapp.controller.exceptions.FacultyNotFound;
import ru.gb.springbootdemoapp.dto.StudentDto;
import ru.gb.springbootdemoapp.dto.StudentShortDto;
import ru.gb.springbootdemoapp.model.Faculty;
import ru.gb.springbootdemoapp.model.Student;
import ru.gb.springbootdemoapp.repository.FacultyRepository;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

  @Autowired
  protected FacultyRepository facultyRepository;

  @Mapping(source = "faculty.title", target = "facultyName")
  public abstract StudentShortDto studentToStudentShortDto(Student student);

  @Mapping(source = "facultyName", target = "faculty", qualifiedByName = "nameToFaculty")
  public abstract Student studentShortDtoToStudent(StudentShortDto studentShortDto);

  @Mappings({
      @Mapping(source = "faculty.title", target = "facultyName")
  })
  public abstract StudentDto studentToStudentDto(Student student);

  @Named("nameToFaculty")
  public Faculty facultyNameToFaculty(String facultyName) {
    return facultyRepository.findByTitle(facultyName)
        .orElseThrow(() -> new FacultyNotFound());
  }
}
