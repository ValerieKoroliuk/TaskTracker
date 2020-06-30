package com.koroliuk.model.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.koroliuk.entity.Attachment;
import com.koroliuk.entity.Comment;
import com.koroliuk.entity.Task;
import com.koroliuk.model.Department;
import com.koroliuk.model.Priority;
import com.koroliuk.model.Status;

public class TaskResponse {
  
  @JsonProperty("task_id")
  private Long id;
  
  @JsonProperty("summary")
  private String summary;
  
  @JsonProperty("description")
  private String description;
  
  @JsonProperty("priority")
  private Priority priority; 
  
  @JsonProperty("status")
  private Status status;
  
  @JsonProperty("dateCreate")
  private LocalDateTime dateCreate;

  @JsonProperty("department")
  private Department department;
  
  @JsonProperty("reporter_id")
  private Long reporterId;
  
  @JsonProperty("assignee_id")
  private Long assigneeId;
  
  @JsonProperty("comment_ids")
  private List<Long> commentsIds = new ArrayList<>();;
  
  @JsonProperty("attachments_ids")
  private List<Long> attachmentsIds = new ArrayList<>();;

  public TaskResponse() {}

  public TaskResponse(Task task) {
    super();
    this.id = task.getId();
    this.summary = task.getSummary();
    this.description = task.getDescription();
    this.priority = task.getPriority();
    this.status = task.getStatus();
    this.dateCreate = task.getDateCreate();
    this.department = task.getDepartment();
    if(task.getReporter().getId() != null) {
      this.reporterId = task.getReporter().getId();
    }
    if(task.getAssignee() != null && task.getAssignee().getId() != null) {
      this.assigneeId = task.getAssignee().getId();
    }
    for (Comment comment : task.getComments()) {
      if (comment != null) {
        commentsIds.add(comment.getId());
      }
    }
    for (Attachment attachment : task.getAttachments()) {
      if (attachment != null) {
        attachmentsIds.add(attachment.getId());
      }
    }
  }

  public Long getId() {
    return id;
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

  public Status getStatus() {
    return status;
  }

  public LocalDateTime getDateCreate() {
    return dateCreate;
  }

  public Department getDepartment() {
    return department;
  }

  public Long getReporterId() {
    return reporterId;
  }

  public Long getAssigneeId() {
    return assigneeId;
  }

  public List<Long> getCommentsIds() {
    return commentsIds;
  }

  public List<Long> getAttachmentsIds() {
    return attachmentsIds;
  }

  public void setId(Long id) {
    this.id = id;
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

  public void setStatus(Status status) {
    this.status = status;
  }

  public void setDateCreate(LocalDateTime dateCreate) {
    this.dateCreate = dateCreate;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public void setReporterId(Long reporterId) {
    this.reporterId = reporterId;
  }

  public void setAssigneeId(Long assigneeId) {
    this.assigneeId = assigneeId;
  }

  public void setCommentsIds(List<Long> commentsIds) {
    this.commentsIds = commentsIds;
  }

  public void setAttachmentsIds(List<Long> attachmentsIds) {
    this.attachmentsIds = attachmentsIds;
  }
  
}
