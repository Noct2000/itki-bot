package com.itki.api.repository;

import com.itki.api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findAllByName(Role.RoleName roleName);
}
