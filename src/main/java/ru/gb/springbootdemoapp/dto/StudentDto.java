package ru.gb.springbootdemoapp.dto;

import lombok.Data;

@Data
public class StudentDto {
  private Long id;
  private String name;
  private String score;
  private String facultyName;
}
