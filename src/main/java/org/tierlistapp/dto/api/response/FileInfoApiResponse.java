package org.tierlistapp.dto.api.response;

public record FileInfoApiResponse(
        String uploadedFileName,
        String fileId,
        Long fileSize
) {}
