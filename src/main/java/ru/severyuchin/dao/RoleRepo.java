package ru.severyuchin.dao;

import org.springframework.data.repository.CrudRepository;
import ru.severyuchin.model.Role;

import java.util.Set;

public interface RoleRepo extends CrudRepository<Role, Long> {
    public Set<Role> findRoleByRole(String role);
}
