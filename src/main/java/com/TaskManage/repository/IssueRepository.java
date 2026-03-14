package com.TaskManage.repository;

import com.TaskManage.entity.Issue;
import com.TaskManage.enums.IssueStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    // Find issue by key
    Optional<Issue> findByIssueKey(String issueKey);

    // Find issues assigned to user
    List<Issue> findByAssignedEmail(String assignedEmail);

    // Find issues in sprint
    List<Issue> findBySprintId(Long sprintId);

    // Find issues by status
    List<Issue> findByIssueStatus(IssueStatus issueStatus);

    // Get backlog issues
    List<Issue> findByProjectIdAndSprintIdIsNullOrderByBackLogPosition(Long projectId);

}