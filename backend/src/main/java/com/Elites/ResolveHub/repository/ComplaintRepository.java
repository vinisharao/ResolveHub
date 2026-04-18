package com.Elites.ResolveHub.repository;

import com.Elites.ResolveHub.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByUserId(Long userId);
}

