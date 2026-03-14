package com.TaskManage.service;

import com.TaskManage.entity.Issue;
import com.TaskManage.entity.Sprint;
import com.TaskManage.enums.IssueType;
import com.TaskManage.enums.SprintState;
import com.TaskManage.repository.IssueRepository;
import com.TaskManage.repository.SprintRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BackLogService {

    private final IssueRepository issueRepo;
    private final SprintRepository sprintRepo;

    public BackLogService(IssueRepository issueRepo, SprintRepository sprintRepo) {
        this.issueRepo = issueRepo;
        this.sprintRepo = sprintRepo;
    }

    // Get backlog issues of project
    public List<Issue> getBackLog(Long projectId) {
        return issueRepo.findByProjectIdAndSprintIdIsNullOrderByBackLogPosition(projectId);
    }

    // Record backlog order
    @Transactional
    public void recordBackLog(Long projectId, List<Long> orderedIssueIds) {

        int position = 0;

        for (Long issueId : orderedIssueIds) {

            Issue issue = issueRepo.findById(issueId)
                    .orElseThrow(() -> new RuntimeException("Issue not found"));

            issue.setBackLogPosition(position++);
            issueRepo.save(issue);
        }
    }

    // Add issue to sprint
    @Transactional
    public Issue addIssueToSprint(Long issueId, Long sprintId) {

        Issue issue = issueRepo.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found"));

        Sprint sprint = sprintRepo.findById(sprintId)
                .orElseThrow(() -> new RuntimeException("Sprint not found"));

        SprintState state = sprint.getSprintState();

        if (state != SprintState.PLANNED && state != SprintState.ACTIVE) {
            throw new RuntimeException("Cannot add issue to sprint in state: " + state);
        }

        issue.setSprintId(sprintId);
        issue.setBackLogPosition(null);

        return issueRepo.save(issue);
    }

    // Backlog hierarchy (Epic → Story → Subtask)
    public Map<String, Object> getBackLogHierarchy(Long projectId) {

        List<Issue> backLog = getBackLog(projectId);

        Map<Long, Map<String, Object>> epicMap = new HashMap<>();
        Map<Long, Issue> storyMap = new HashMap<>();

        // Identify Epics and Stories
        for (Issue issue : backLog) {

            if (issue.getIssueType() == IssueType.EPICS) {

                Map<String, Object> epicData = new HashMap<>();

                epicData.put("epic", issue);
                epicData.put("stories", new ArrayList<Issue>());
                epicData.put("subtasks", new HashMap<Long, List<Issue>>());

                epicMap.put(issue.getId(), epicData);
            }

            if (issue.getIssueType() == IssueType.STORIES) {
                storyMap.put(issue.getId(), issue);
            }
        }

        // Attach Stories to Epics
        for (Issue issue : backLog) {

            if (issue.getIssueType() == IssueType.STORIES) {

                Long epicId = issue.getEpicId();

                if (epicId != null && epicMap.containsKey(epicId)) {

                    List<Issue> stories =
                            (List<Issue>) epicMap.get(epicId).get("stories");

                    stories.add(issue);
                }
            }
        }

        // Attach Subtasks to Stories
        for (Issue issue : backLog) {

            if (issue.getIssueType() == IssueType.SUB_TASKS) {

                Long parentId = issue.getParentIssueId();

                if (parentId != null && storyMap.containsKey(parentId)) {

                    for (Map<String, Object> epicData : epicMap.values()) {

                        List<Issue> stories =
                                (List<Issue>) epicData.get("stories");

                        boolean belongsToEpic = stories.stream()
                                .anyMatch(s -> s.getId().equals(parentId));

                        if (belongsToEpic) {

                            Map<Long, List<Issue>> subMap =
                                    (Map<Long, List<Issue>>) epicData.get("subtasks");

                            subMap.computeIfAbsent(parentId,
                                    k -> new ArrayList<>()).add(issue);

                            break;
                        }
                    }
                }
            }
        }

        return Collections.singletonMap("epics", epicMap);
    }
}