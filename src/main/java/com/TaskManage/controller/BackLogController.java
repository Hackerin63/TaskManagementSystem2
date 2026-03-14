package com.TaskManage.controller;

import com.TaskManage.entity.Issue;

import com.TaskManage.service.BackLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/backlog")
public class BackLogController {

    private final BackLogService backLogService;

    public BackLogController(BackLogService backLogService) {
        this.backLogService = backLogService;
    }

    // Get Backlog Issues of a Project
    @GetMapping("/{projectId}")
    public ResponseEntity<List<Issue>> getBackLog(@PathVariable Long projectId) {
        return ResponseEntity.ok(backLogService.getBackLog(projectId));
    }

    // Record Backlog order
    @PostMapping("/{projectId}/record")
    public ResponseEntity<String> recordBackLog(
            @PathVariable Long projectId,
            @RequestBody List<Long> orderedIssueIds) {

        backLogService.recordBackLog(projectId, orderedIssueIds);
        return ResponseEntity.ok("Backlog recorded successfully");
    }

    // Add Issue to Sprint
    @PutMapping("/add-to-sprint/{issueId}/{sprintId}")
    public ResponseEntity<Issue> addIssueToSprint(
            @PathVariable Long issueId,
            @PathVariable Long sprintId) {

        return ResponseEntity.ok(backLogService.addIssueToSprint(issueId, sprintId));
    }

    // Get Backlog Hierarchy (Epic -> Story -> Subtask)
    @GetMapping("/{projectId}/hierarchy")
    public ResponseEntity<Map<String, Object>> getHierarchy(@PathVariable Long projectId) {

        return ResponseEntity.ok(backLogService.getBackLogHierarchy(projectId));
    }
}