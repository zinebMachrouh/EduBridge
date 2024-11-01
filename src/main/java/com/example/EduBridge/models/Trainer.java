package com.example.EduBridge.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "Trainers")
@DiscriminatorValue("Trainer")
public class Trainer extends User {

    @Column(name = "speciality", nullable = false)
    private String speciality;

    @OneToOne(mappedBy = "trainer")
    private Class classEntity;
}
