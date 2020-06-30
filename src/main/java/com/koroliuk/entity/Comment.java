package com.koroliuk.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreationTimestamp
  private LocalDateTime dateCreate;

  private String text;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User author;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "task_id")
  private Task task;

  public Comment() {}

  public Long getId() {
    return id;
  }

  public LocalDateTime getDateCreate() {
    return dateCreate;
  }

  public String getText() {
    return text;
  }

  public User getAuthor() {
    return author;
  }

  public Task getTask() {
    return task;
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

  public void setAuthor(User author) {
    this.author = author;
  }

  public void setTask(Task task) {
    this.task = task;
  }

}
