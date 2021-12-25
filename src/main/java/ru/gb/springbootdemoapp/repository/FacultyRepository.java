package ru.gb.springbootdemoapp.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springbootdemoapp.model.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

  Optional<Faculty> findByTitle(String title);
}
