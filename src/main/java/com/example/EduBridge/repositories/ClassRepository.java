package com.example.EduBridge.repositories;

import com.example.EduBridge.models.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Class,Long> {
    Optional<Class> findByNameAndRoomNumber(String name, Integer roomNumber);
}
