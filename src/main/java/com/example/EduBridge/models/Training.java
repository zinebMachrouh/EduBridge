package com.example.EduBridge.models;

import com.example.EduBridge.models.enums.TrainingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "Trainings")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name="level", nullable = false)
    private String level;

    @Column(name ="prerequisites", nullable = false)
    private String prerequisites;

    @Column(name = "minCapacity", nullable = false)
    private int minCapacity;

    @Column(name = "maxCapacity", nullable = false)
    private int maxCapacity;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Column(name = "endDate", nullable = false)
    private Date endDate;

    @Column(name = "status", nullable = false)
    private TrainingStatus status;

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Class> classes = new ArrayList<>();

}
