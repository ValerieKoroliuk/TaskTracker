package com.koroliuk.model.response;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.koroliuk.entity.Comment;
import com.koroliuk.entity.Task;
import com.koroliuk.entity.User;
import com.koroliuk.model.Department;

public class UserResponse {

  @JsonProperty("user_id")
  private Long id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("department")
  private Department department;

  @JsonProperty("comments_ids")
  private List<Long> commentsIds = new ArrayList<>();

  @JsonProperty("tasks_ids")
  private List<Long> tasksIds = new ArrayList<>();

  public UserResponse() {}

  public UserResponse(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.department = user.getDepartment();

    for (Comment comment : user.getComments()) {
      if (comment != null) {
        commentsIds.add(comment.getId());
      }
    }

    for (Task task : user.getTasks()) {
      if (task != null) {
        commentsIds.add(task.getId());
      }
    }
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Department getDepartment() {
    return department;
  }

  public List<Long> getCommentsIds() {
    return commentsIds;
  }

  public List<Long> getTasksIds() {
    return tasksIds;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public void setCommentsIds(List<Long> commentsIds) {
    this.commentsIds = commentsIds;
  }

  public void setTasksIds(List<Long> tasksIds) {
    this.tasksIds = tasksIds;
  }
    
}
