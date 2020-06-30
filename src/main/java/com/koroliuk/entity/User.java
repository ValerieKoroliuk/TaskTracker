package com.koroliuk.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.koroliuk.model.Department;

@Entity
@Table(name = "usr")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  
  @Enumerated(EnumType.STRING)
  private Department department;

  @OneToMany(fetch = FetchType.EAGER)
  private List<Task> tasks;
  
  @OneToMany(fetch = FetchType.EAGER)
  private List<Comment> comments;

  public User() {}

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Department getDepartment() {
    return department;
  }
 
  public List<Task> getTasks() {
    if(tasks == null) {
      tasks = new ArrayList<>();
    }
    return tasks;
  }

  public List<Comment> getComments() {
    if(comments == null) {
      comments = new ArrayList<>();
    }
    return comments;
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

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

}
