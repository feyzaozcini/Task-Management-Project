package com.turkcell.common.events;

import com.turkcell.common.ProjectGetResponse;
import com.turkcell.common.UserGetResponse;

import java.time.LocalDateTime;
import java.util.List;

public class TaskEvent {
    private Integer id;
    private String taskName;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private ProjectGetResponse project;
    private List<UserGetResponse> users;

    public TaskEvent(Integer id, String taskName, LocalDateTime startDate, LocalDateTime endDate, ProjectGetResponse project, List<UserGetResponse> users) {
        this.id = id;
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.project = project;
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    public ProjectGetResponse getProject() {
        return project;
    }

    public void setProject(ProjectGetResponse project) {
        this.project = project;
    }

    public List<UserGetResponse> getUsers() {
        return users;
    }

    public void setUsers(List<UserGetResponse> users) {
        this.users = users;
    }
}
