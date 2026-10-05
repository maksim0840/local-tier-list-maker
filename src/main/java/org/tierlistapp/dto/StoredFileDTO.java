package org.tierlistapp.dto;

import java.time.Instant;

public record StoredFileDTO(
    String id,
    String ownerId,
    String uploadedFileName,
    String objectKey,
    Instant createdAt
) {}
