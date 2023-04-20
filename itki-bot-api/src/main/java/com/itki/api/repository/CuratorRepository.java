package com.itki.api.repository;

import com.itki.api.model.Curator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuratorRepository extends JpaRepository<Curator, Long> {
}
