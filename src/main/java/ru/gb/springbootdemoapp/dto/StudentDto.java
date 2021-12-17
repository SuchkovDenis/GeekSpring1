package ru.gb.springbootdemoapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentDto {
  private Long id;
  private String name;
  private String score;
  private String facultyName;
}
