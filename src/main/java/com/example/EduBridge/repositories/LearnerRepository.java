package com.example.EduBridge.repositories;

import com.example.EduBridge.models.Learner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearnerRepository extends JpaRepository<Learner, String> { }
