package com.koroliuk.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koroliuk.model.Department;
import com.koroliuk.model.Priority;

public class TaskRequest {
  
  @JsonProperty("user_id")
  private Long userId;

  @JsonProperty("summary")
  private String summary;

  @JsonProperty("description")
  private String description;

  @JsonProperty("priority")
  private Priority priority;

  @JsonProperty("department")
  private Department department;

  public Long getUserId() {
    return userId;
  }

  public String getSummary() {
    return summary;
  }

  public String getDescription() {
    return description;
  }

  public Priority getPriority() {
    return priority;
  }

  public Department getDepartment() {
    return department;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setPriority(Priority priority) {
    this.priority = priority;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

}
