package com.TaskManage.repository;

import com.TaskManage.entity.Sprint;
import com.TaskManage.enums.SprintState;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

    // Get all sprints for a project
    List<Sprint> findByProjectId(Long projectId);

    // Get sprints by state
    List<Sprint> findBySprintState(SprintState sprintState);

}