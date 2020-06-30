package com.koroliuk.repository;

import org.springframework.data.repository.CrudRepository;
import com.koroliuk.entity.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}