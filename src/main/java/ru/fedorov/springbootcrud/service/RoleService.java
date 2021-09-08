package ru.fedorov.springbootcrud.service;

import java.util.List;
import ru.fedorov.springbootcrud.model.Role;

public interface RoleService {

    Role getRole(String role);

    List<Role> getAllRoles();
}
