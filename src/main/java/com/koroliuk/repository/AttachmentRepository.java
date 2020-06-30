package com.koroliuk.repository;

import org.springframework.data.repository.CrudRepository;
import com.koroliuk.entity.Attachment;

public interface AttachmentRepository extends CrudRepository<Attachment, Long> {

}