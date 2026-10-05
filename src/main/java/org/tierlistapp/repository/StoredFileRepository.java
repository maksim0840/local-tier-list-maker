package org.tierlistapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tierlistapp.entity.StoredFile;

public interface StoredFileRepository extends MongoRepository<StoredFile, String> {}
