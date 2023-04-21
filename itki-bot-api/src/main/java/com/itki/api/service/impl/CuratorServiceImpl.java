package com.itki.api.service.impl;

import com.itki.api.model.Curator;
import com.itki.api.repository.CuratorRepository;
import com.itki.api.repository.GroupRepository;
import com.itki.api.service.CuratorService;
import org.springframework.stereotype.Service;

@Service
public class CuratorServiceImpl extends CrudServiceImpl<Curator> implements CuratorService {
  private final GroupRepository groupRepository;

  public CuratorServiceImpl(
      CuratorRepository curatorRepository,
      GroupRepository groupRepository
  ) {
    super(curatorRepository, Curator.class.getSimpleName());
    this.groupRepository = groupRepository;
  }

  @Override
  public Long getGroupsCountByCuratorId(Long curatorId) {
    return groupRepository.countAllByCuratorId(curatorId);
  }
}
