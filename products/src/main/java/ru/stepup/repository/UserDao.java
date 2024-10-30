package ru.stepup.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.stepup.dto.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    @NonNull
    Optional<User> findById(@NonNull Long id);
    @NonNull
    List<User> findAll();
}
