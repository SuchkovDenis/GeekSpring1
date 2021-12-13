package ru.gb.springbootdemoapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.springbootdemoapp.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  @Query("select s from Student s left join fetch s.faculty where s.score >= :score")
  List<Student> findAllByScoreGreaterThanEqual(Float score);
}
