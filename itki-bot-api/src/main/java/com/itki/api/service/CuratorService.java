package com.itki.api.service;

import com.itki.api.model.Curator;

public interface CuratorService extends CrudService<Curator> {
  Long getGroupsCountByCuratorId(Long curatorId);
}
