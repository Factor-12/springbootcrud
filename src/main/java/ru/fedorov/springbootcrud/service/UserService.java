package ru.fedorov.springbootcrud.service;

import java.util.List;
import ru.fedorov.springbootcrud.model.User;

public interface UserService {
    void save(User user);
    List<User> getAll();
    void removeUser(long id);
    User getById(long id);
}
