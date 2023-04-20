package com.itki.api.service.impl;

import com.itki.api.model.Curator;
import com.itki.api.repository.CuratorRepository;
import com.itki.api.service.CuratorService;
import org.springframework.stereotype.Service;

@Service
public class CuratorServiceImpl extends CrudServiceImpl<Curator> implements CuratorService {
  public CuratorServiceImpl(CuratorRepository curatorRepository) {
    super(curatorRepository, Curator.class.getSimpleName());
  }
}
