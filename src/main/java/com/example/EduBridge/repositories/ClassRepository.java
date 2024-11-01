package com.example.EduBridge.repositories;

import com.example.EduBridge.models.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class,String> { }
