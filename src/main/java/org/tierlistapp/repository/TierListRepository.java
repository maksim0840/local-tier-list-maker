package org.tierlistapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tierlistapp.entity.TierList;

public interface TierListRepository extends MongoRepository<TierList, String> {
}
