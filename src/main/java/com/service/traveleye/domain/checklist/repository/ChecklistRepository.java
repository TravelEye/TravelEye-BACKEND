package com.service.traveleye.domain.checklist.repository;

import com.service.traveleye.domain.checklist.entity.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChecklistRepository  extends JpaRepository<Checklist,Long> {

    @Query(value = "SELECT * FROM checklist_test WHERE checklist_id = :id", nativeQuery = true)
    List<Checklist> findByChecklistId(@Param("id") Long id);
    @Query(value = "SELECT * FROM checklist_test WHERE member_id = :id", nativeQuery = true)
    List<Checklist> findByMemberId(@Param("id") Long id);
    @Modifying
    @Query(value = "DELETE FROM checklist_test WHERE checklist_id = :id", nativeQuery = true)
    Integer deleteChecklistByChecklistId(@Param("id") Long id);

}
