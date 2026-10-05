package org.tierlistapp.dto;

import org.tierlistapp.entity.TierMap;

import java.time.Instant;
import java.util.Map;

public record TierListDTO(
    String id,
    String userId,
    TierMap tierMap,
    Instant createdAt,
    Instant updatedAt
) {}
