package com.service.traveleye.domain.checklist.repository;

import com.service.traveleye.domain.checklist.entity.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChecklistRepository  extends JpaRepository<Checklist,Long> {
Optional<Checklist> findById(Long id);
void deleteById(Long id);
}
