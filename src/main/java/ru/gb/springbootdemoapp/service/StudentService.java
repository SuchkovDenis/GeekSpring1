package ru.gb.springbootdemoapp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.gb.springbootdemoapp.model.Student;
import ru.gb.springbootdemoapp.repository.StudentRepository;

@Service
public class StudentService {
  private StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> getAll() {
    return studentRepository.findAll();
  }

  public List<Student> getWithScore(Float score) {
    return studentRepository.findAllByScoreGreaterThanEqual(score);
  }

  public void save(Student student) {
    studentRepository.save(student);
  }

  public Student findById(Long id) {
    return studentRepository.findById(id).orElse(null);
  }

  public void deleteById(Long id) {
    studentRepository.deleteById(id);
  }
}
