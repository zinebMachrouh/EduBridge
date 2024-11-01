package com.example.EduBridge.models;
import lombok.*;
import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@DiscriminatorValue("Learners")
public class Learner extends User{
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id", nullable = false)
    private Class classEntity;
}
