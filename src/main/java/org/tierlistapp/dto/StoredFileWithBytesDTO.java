package org.tierlistapp.dto;

import java.time.Instant;

public record StoredFileWithBytesDTO(
        String id,
        String ownerId,
        String uploadedFileName,
        String objectKey,
        Instant createdAt,
        byte[] fileBytes
) {
    public StoredFileWithBytesDTO(StoredFileDTO storedFileDTO, byte[] fileBytes) {
        this(
                storedFileDTO.id(),
                storedFileDTO.ownerId(),
                storedFileDTO.uploadedFileName(),
                storedFileDTO.objectKey(),
                storedFileDTO.createdAt(),
                fileBytes
        );
    }
}
