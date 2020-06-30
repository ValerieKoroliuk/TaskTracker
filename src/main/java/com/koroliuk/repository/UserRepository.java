package com.koroliuk.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.koroliuk.entity.User;
import com.koroliuk.model.Department;

public interface UserRepository extends CrudRepository<User, Long> {
  
  List<User> findAllByDepartment(Department department);
  User findByName(String name);
  
}