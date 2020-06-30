package com.koroliuk.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.CreationTimestamp;
import com.koroliuk.model.Department;
import com.koroliuk.model.Priority;
import com.koroliuk.model.Status;

@Entity
public class Task {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  
  private String summary;
  
  private String description;
  
  @Enumerated(EnumType.STRING)
  private Priority priority; 
  
  @Enumerated(EnumType.STRING)
  private Status status;
  
  @CreationTimestamp
  private LocalDateTime dateCreate;

  @Enumerated(EnumType.STRING)
  private Department department;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "reporter")
  private User reporter;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "assignee")
  private User assignee;
  
  @OneToMany(fetch = FetchType.EAGER)
  private List<Comment> comments;
  
  @OneToMany(fetch = FetchType.EAGER)
  private List<Attachment> attachments;
  
  public Task() {
    status = Status.PRODUCT_BACKLOG;
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

  public User getReporter() {
    return reporter;
  }

  public User getAssignee() {
    return assignee;
  }

  public List<Comment> getComments() {
    if(comments == null) {
      comments = new ArrayList<>();
    }
    return comments;
  }

  public List<Attachment> getAttachments() {
    if(attachments == null) {
      attachments = new ArrayList<>();
    }
    return attachments;
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

  public void setReporter(User reporter) {
    this.reporter = reporter;
  }

  public void setAssignee(User assignee) {
    this.assignee = assignee;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public void setAttachments(List<Attachment> attachments) {
    this.attachments = attachments;
  }
  
}