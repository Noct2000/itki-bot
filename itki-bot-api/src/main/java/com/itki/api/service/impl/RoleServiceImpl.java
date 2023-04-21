package com.itki.api.service.impl;

import com.itki.api.model.Role;
import com.itki.api.repository.RoleRepository;
import com.itki.api.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends CrudServiceImpl<Role> implements RoleService {
  private final RoleRepository roleRepository;

  public RoleServiceImpl(RoleRepository roleRepository) {
    super(roleRepository, Role.class.getSimpleName());
    this.roleRepository = roleRepository;
  }

  @Override
  public Role findByName(Role.RoleName roleName) {
    return roleRepository.findRoleByName(roleName).orElseThrow(
      () -> new RuntimeException("No role by roleName: " + roleName.name())
    );
  }
}
