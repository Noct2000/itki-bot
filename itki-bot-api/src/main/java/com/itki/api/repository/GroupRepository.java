package com.itki.api.repository;

import com.itki.api.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
  Long countAllByCuratorId(Long curatorId);
}
