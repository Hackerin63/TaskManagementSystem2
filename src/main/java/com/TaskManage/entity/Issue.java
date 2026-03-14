package com.TaskManage.entity;

import com.TaskManage.enums.IssuePriority;
import com.TaskManage.enums.IssueStatus;
import com.TaskManage.enums.IssueType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.time.LocalDateTime;

@Entity
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String issueTitle;

    @Column(unique = true, nullable = false)
    private String issueKey;

    @Column(length = 2000)
    private String issueDescription;

    @Enumerated(EnumType.STRING)
    private IssueType issueType;

    @Enumerated(EnumType.STRING)
    private IssueStatus issueStatus;

    @Enumerated(EnumType.STRING)
    private IssuePriority priority;

    private String assignedEmail;
    private String reporterEmail;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private LocalDateTime dueDate;

    private Long sprintId;
    private Long epicId;
    private Long parentIssueId;
    private Long projectId;

    private Integer backLogPosition;

    // Constructors
    public Issue() {
    }

    public Issue(Long id, String issueTitle, String issueKey, String issueDescription,
                 IssueType issueType, IssueStatus issueStatus, IssuePriority priority,
                 String assignedEmail, String reporterEmail, Long projectId) {
        this.id = id;
        this.issueTitle = issueTitle;
        this.issueKey = issueKey;
        this.issueDescription = issueDescription;
        this.issueType = issueType;
        this.issueStatus = issueStatus;
        this.priority = priority;
        this.assignedEmail = assignedEmail;
        this.reporterEmail = reporterEmail;
        this.projectId = projectId;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIssueTitle() {
        return issueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        this.issueTitle = issueTitle;
    }

    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public IssueStatus getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(IssueStatus issueStatus) {
        this.issueStatus = issueStatus;
    }

    public IssuePriority getPriority() {
        return priority;
    }

    public void setPriority(IssuePriority priority) {
        this.priority = priority;
    }

    public String getAssignedEmail() {
        return assignedEmail;
    }

    public void setAssignedEmail(String assignedEmail) {
        this.assignedEmail = assignedEmail;
    }

    public String getReporterEmail() {
        return reporterEmail;
    }

    public void setReporterEmail(String reporterEmail) {
        this.reporterEmail = reporterEmail;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Long getSprintId() {
        return sprintId;
    }

    public void setSprintId(Long sprintId) {
        this.sprintId = sprintId;
    }

    public Long getEpicId() {
        return epicId;
    }

    public void setEpicId(Long epicId) {
        this.epicId = epicId;
    }

    public Long getParentIssueId() {
        return parentIssueId;
    }

    public void setParentIssueId(Long parentIssueId) {
        this.parentIssueId = parentIssueId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getBackLogPosition() {
        return backLogPosition;
    }

    public void setBackLogPosition(Integer backLogPosition) {
        this.backLogPosition = backLogPosition;
    }
}