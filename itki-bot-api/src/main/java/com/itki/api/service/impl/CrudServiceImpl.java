package com.itki.api.service.impl;

import com.itki.api.service.CrudService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

@RequiredArgsConstructor
public abstract class CrudServiceImpl<T> implements CrudService<T> {
  private final JpaRepository<T, Long> repository;
  private final String entityName;

  @Override
  public T findById(Long id) {
    return repository.findById(id)
      .orElseThrow(() -> new RuntimeException(
        String.format("No entity: '%s' with id: %d", entityName, id)
      ));
  }

  @Override
  public List<T> findAll() {
    return repository.findAll();
  }

  @Override
  public T save(T entity) {
    return repository.save(entity);
  }
}
