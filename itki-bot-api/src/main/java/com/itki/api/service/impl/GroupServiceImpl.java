package com.itki.api.service.impl;

import com.itki.api.model.Group;
import com.itki.api.repository.GroupRepository;
import com.itki.api.service.GroupService;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl extends CrudServiceImpl<Group> implements GroupService {
  public GroupServiceImpl(GroupRepository groupRepository) {
    super(groupRepository, Group.class.getSimpleName());
  }
}
