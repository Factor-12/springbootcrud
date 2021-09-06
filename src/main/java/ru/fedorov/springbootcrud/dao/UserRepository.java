package ru.fedorov.springbootcrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fedorov.springbootcrud.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
