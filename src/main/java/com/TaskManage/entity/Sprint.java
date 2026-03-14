package com.TaskManage.entity;

import com.TaskManage.enums.SprintState;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sprints")
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sprintName;

    private String description;

    private Long projectId;

    @Enumerated(EnumType.STRING)
    private SprintState sprintState;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructors
    public Sprint() {
    }

    public Sprint(Long id, String sprintName, String description, Long projectId,
                  SprintState sprintState, LocalDateTime startDate,
                  LocalDateTime endDate) {
        this.id = id;
        this.sprintName = sprintName;
        this.description = description;
        this.projectId = projectId;
        this.sprintState = sprintState;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public SprintState getSprintState() {
        return sprintState;
    }

    public void setSprintState(SprintState sprintState) {
        this.sprintState = sprintState;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}