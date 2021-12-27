package ru.gb.springbootdemoapp.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springbootdemoapp.model.GeekUser;

@Repository
public interface UserRepository extends JpaRepository<GeekUser, Long> {

  Optional<GeekUser> findByName(String name);
}
