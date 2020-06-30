package com.koroliuk.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.koroliuk.entity.Task;
import com.koroliuk.model.Department;

public interface TaskRepository extends CrudRepository<Task, Long> {
  
  List<Task> findAllByDepartment(Department department);

}