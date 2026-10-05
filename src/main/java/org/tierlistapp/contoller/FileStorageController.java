package org.tierlistapp.contoller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tierlistapp.dto.StoredFileDTO;
import org.tierlistapp.dto.StoredFileWithBytesDTO;
import org.tierlistapp.dto.api.response.FileInfoApiResponse;
import org.tierlistapp.enums.TierEntityType;
import org.tierlistapp.service.FileStorageService;

import java.io.IOException;

@RestController
@RequestMapping("/api/storage")
public class FileStorageController {

    private final FileStorageService fileStorageService;

    public FileStorageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping(value = "/upload/tier-img", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FileInfoApiResponse uploadTierListImg(
            @RequestParam TierEntityType tierEntityType,
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        String userId = "user_id_0000_test";

        StoredFileDTO fileInfo = fileStorageService.saveTierEntityFile(userId, tierEntityType, file);
        return new FileInfoApiResponse(fileInfo.uploadedFileName(), fileInfo.id(), file.getSize());
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String id) {
        String userId = "user_id_0000_test";

        StoredFileWithBytesDTO fileInfo = fileStorageService.getTierEntityFileByIdAndOwnerId(id, userId);
        return ResponseEntity.ok()
                .contentLength(fileInfo.fileBytes().length)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileInfo.uploadedFileName() + "\""
                )
                .body(fileInfo.fileBytes());
    }
}
