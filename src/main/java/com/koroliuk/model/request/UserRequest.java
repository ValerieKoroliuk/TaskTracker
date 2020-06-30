package com.koroliuk.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koroliuk.model.Department;

public class UserRequest {

  @JsonProperty("name")
  private String name;

  @JsonProperty("department")
  private Department department;

  public String getName() {
    return name;
  }

  public Department getDepartment() {
    return department;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }
  
}
