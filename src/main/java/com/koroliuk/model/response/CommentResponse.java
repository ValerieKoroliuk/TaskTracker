package com.koroliuk.model.response;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.koroliuk.entity.Comment;

public class CommentResponse { 
  
  @JsonProperty("comment_id")
  private Long id;

  @JsonProperty("dateCreate")
  private LocalDateTime dateCreate;

  @JsonProperty("text")
  private String text;
  
  @JsonProperty("user_id")
  private Long userId;
  
  @JsonProperty("task_id")
  private Long taskId;

  public CommentResponse() {}
 
  public CommentResponse(Comment comment) {
    this.id = comment.getId();
    this.dateCreate = comment.getDateCreate();
    this.text = comment.getText();
    this.userId = comment.getAuthor().getId();
    this.taskId = comment.getTask().getId();
  }

  public Long getId() {
    return id;
  }

  public LocalDateTime getDateCreate() {
    return dateCreate;
  }

  public String getText() {
    return text;
  }

  public Long getUserId() {
    return userId;
  }

  public Long getTaskId() {
    return taskId;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setDateCreate(LocalDateTime dateCreate) {
    this.dateCreate = dateCreate;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setTaskId(Long taskId) {
    this.taskId = taskId;
  }
}
