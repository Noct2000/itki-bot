package com.itki.api.service;

import com.itki.api.model.Role;

public interface RoleService extends CrudService<Role> {
  Role findByName(Role.RoleName roleName);
}
