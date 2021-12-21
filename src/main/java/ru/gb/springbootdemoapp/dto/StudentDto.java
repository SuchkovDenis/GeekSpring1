package ru.gb.springbootdemoapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JacksonXmlRootElement(localName = "student")
public class StudentDto {
  @JsonIgnore
  private Long id;
  private String name;
  private String score;
  @JsonProperty("faculty_name")
  @JacksonXmlProperty(isAttribute = true)
  private String facultyName;
}
