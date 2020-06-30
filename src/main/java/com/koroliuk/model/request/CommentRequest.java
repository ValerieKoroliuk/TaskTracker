package com.koroliuk.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentRequest {
  
  @JsonProperty("user_id")
  private Long userId;
  
  @JsonProperty("task_id")
  private Long taskId;

  @JsonProperty("text")
  private String text;

  public Long getUserId() {
    return userId;
  }

  public Long getTaskId() {
    return taskId;
  }

  public String getText() {
    return text;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setTaskId(Long taskId) {
    this.taskId = taskId;
  }

  public void setText(String text) {
    this.text = text;
  }
  
}
