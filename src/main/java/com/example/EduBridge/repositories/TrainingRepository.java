package com.example.EduBridge.repositories;

import com.example.EduBridge.models.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    Page<Training> findByTitleContainingIgnoreCase(String title, Pageable pageable);

}