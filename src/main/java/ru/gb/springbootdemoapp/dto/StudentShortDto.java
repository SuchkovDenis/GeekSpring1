package ru.gb.springbootdemoapp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentShortDto {

  @NotNull
  @NotEmpty(message = "{validation.NotEmpty.message}")
  private String name;

  @Min(value = 0, message = "{validation.Min.message}")
  @Max(value = 100, message = "{validation.Max.message}")
  private Float score;
}
